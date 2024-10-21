package toys.timberix.promack.betterstack.dto

import kotlinx.serialization.Serializable

@Serializable
data class MonitorResponse(
    val data: List<Monitor>
)
