package toys.timberix.promack.betterstack.dto

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.time.Duration.Companion.seconds

@Serializable
data class ResponseTime(
    val at: Instant,
    @SerialName("response_time")
    val responseTimeSeconds: Double
) {
    @Transient
    val responseTime = responseTimeSeconds.seconds
}
