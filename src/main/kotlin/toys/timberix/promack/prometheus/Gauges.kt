package toys.timberix.promack.prometheus

import io.prometheus.metrics.core.metrics.Gauge

object Gauges {
    private fun gauge(name: String, description: String, vararg labelNames: String) =
        Gauge.builder().name("promack_$name").help(description).labelNames(*labelNames).register()

    val responseTime: Gauge = gauge(
        "latency",
        "Latency of individual monitors",
        "monitor", "region"
    )
}