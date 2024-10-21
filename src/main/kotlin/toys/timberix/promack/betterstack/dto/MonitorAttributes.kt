package toys.timberix.promack.betterstack.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MonitorAttributes(
    val url: String,
    @SerialName("pronounceable_name")
    val pronounceableName: String,
)
