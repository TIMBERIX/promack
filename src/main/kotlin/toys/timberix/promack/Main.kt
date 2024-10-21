package toys.timberix.promack

import io.prometheus.metrics.exporter.httpserver.HTTPServer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import toys.timberix.promack.betterstack.APIClient
import toys.timberix.promack.config.ConfigManager
import toys.timberix.promack.prometheus.Gauges
import kotlin.time.Duration.Companion.seconds


fun main() = runBlocking {
    logger.info("Initializing...")

    Gauges
    launch(Dispatchers.IO) {
        while (true) {
            logger.finer("Fetching data...")
            runCatching {
                APIClient.fetch()
            }.onFailure {
                it.printStackTrace()
                logger.warning("Error while fetching data from Betterstack: ${it.message}")
            }
            delay(ConfigManager.interval.seconds)
        }
    }

    logger.info("Initialized")

    logger.info("Starting prometheus metrics endpoint...")
    HTTPServer.builder().port(ConfigManager.port).buildAndStart();
    logger.info("Prometheus metrics endpoint listening on :${ConfigManager.port}")
}
