
# promack

A prometheus exporter for Betterstack uptime data. Fetches data from Betterstack using the API and exposes it via `/metrics`.

## Metrics

- Uptime latency data: the latest response time per region and 

## Using with docker

Add the following to your `docker-compose.yml` file:

```yaml
services:
  # ...
  # other services
  # ...
  
  betterstack-exporter:
    image: timberix/promack
    restart: unless-stopped
    ports:
      - 8081:8081
    environment:
      PROMACK_BETTERSTACK_TOKEN: "your-betterstack-uptime-token" # required!
      # PROMACK_PORT: 8081 # optional
      # PROMACK_INTERVAL: 30 # optional, in seconds (Betterstack takes a long time to update anyway)
      # PROMACK_BETTERSTACK_API: "https://uptime.betterstack.com/api/v2" # optional
```
