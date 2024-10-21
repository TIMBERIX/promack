package toys.timberix.promack.betterstack.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseTimesRegion(
    val region: String,
    @SerialName("response_times")
    val responseTimes: List<ResponseTime>
)
