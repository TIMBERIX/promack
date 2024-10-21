package toys.timberix.promack.betterstack.dto

import kotlinx.serialization.Serializable

@Serializable
data class Monitor(
    val id: Int,
    val attributes: MonitorAttributes
)
