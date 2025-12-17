#!/bin/bash

# Construa a imagem Docker
echo "[INFO] Construindo a imagem Docker do FX"

docker images --format "table {{.Repository}}\t{{.Tag}}\t{{.Size}}" \
  | grep fx \
  | sort -k3 -h || true
docker rmi -f fx:latest || true

export DOCKER_BUILDKIT=1
export $(cat .env | xargs)
docker build --pull --no-cache \
  --build-arg hostname=ms-offers-importer \
  --build-arg SPRING_PROFILES_ACTIVE=$SPRING_PROFILES_ACTIVE \
  --build-arg INSTANCE_ID=$HOSTNAME \
  --build-arg OTEL_SERVICE_NAME=fx \
  --build-arg OTEL_RESOURCE_ATTRIBUTES="service.name=fx,service.instance.id=$(hostname)" \
  --build-arg OTEL_EXPORTER_OTLP_ENDPOINT=$OTEL_EXPORTER_OTLP_ENDPOINT \
  --build-arg OTEL_EXPORTER_OTLP_LOGS_ENDPOINT=$OTEL_EXPORTER_OTLP_LOGS_ENDPOINT \
  --build-arg OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=$OTEL_EXPORTER_OTLP_TRACES_ENDPOINT \
  --build-arg OTEL_EXPORTER_OTLP_METRICS_ENDPOINT=$OTEL_EXPORTER_OTLP_METRICS_ENDPOINT \
  -t fx:latest \
  --progress=plain .

docker save fx:latest -o fx.tar
docker load -i fx.tar
docker images --format "table {{.Repository}}\t{{.Tag}}\t{{.Size}}" \
  | grep fx \
  | sort -k3 -h