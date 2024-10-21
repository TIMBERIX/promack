package toys.timberix.promack.betterstack

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import toys.timberix.promack.betterstack.dto.MonitorResponse
import toys.timberix.promack.betterstack.dto.ResponseTimesResponse
import toys.timberix.promack.config.ConfigManager
import toys.timberix.promack.logger
import toys.timberix.promack.prometheus.Gauges

object APIClient {
    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }
    private val baseUrl get() = ConfigManager.betterstackApi

    private fun HttpRequestBuilder.auth() {
        header("Authorization", "Bearer ${ConfigManager.betterstackToken}")
    }

    /**
     * Fetch data from the Betterstack API and store it in the Prometheus registry.
     *
     * Gets called every X seconds from the main loop.
     */
    suspend fun fetch() {
        val response = client.get("$baseUrl/monitors") {
            auth()
        }
        val monitors = response.body<MonitorResponse>().data
        monitors.forEach { monitor ->
            val response1 = client.get("$baseUrl/monitors/${monitor.id}/response-times") {
                auth()
            }
            val responseTimes = response1.body<ResponseTimesResponse>().data
            responseTimes.attributes.regions.forEach { region ->
                val responseTime = region.responseTimes.maxByOrNull { it.at }!!.responseTimeSeconds
                Gauges.responseTime.labelValues(monitor.attributes.pronounceableName, region.region).set(responseTime)
            }
            logger.fine("Fetched response times for monitor ${monitor.attributes.pronounceableName}")
        }
    }
}
