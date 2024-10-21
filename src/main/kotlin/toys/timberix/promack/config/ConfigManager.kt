package toys.timberix.promack.config

object ConfigManager {
    private fun configValue(key: String) = System.getenv("PROMACK_${key.uppercase()}")

    val port = configValue("port")?.toInt() ?: 8081
    val interval = configValue("interval")?.toInt() ?: 30
    val betterstackApi = configValue("betterstack_api") ?: "https://uptime.betterstack.com/api/v2"
    val betterstackToken = configValue("betterstack_token") ?: error("Please provide the Betterstack API token environment variable.")
}