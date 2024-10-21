package toys.timberix.promack.betterstack.dto

import kotlinx.serialization.Serializable

@Serializable
data class ResponseTimesAttributes(
    val regions: List<ResponseTimesRegion>,
)
