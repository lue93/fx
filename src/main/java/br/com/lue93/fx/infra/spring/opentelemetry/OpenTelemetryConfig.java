package br.com.lue93.fx.infra.spring.opentelemetry;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.http.logs.OtlpHttpLogRecordExporter;
import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.logs.SdkLoggerProvider;
import io.opentelemetry.sdk.logs.export.BatchLogRecordProcessor;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.opentelemetry.semconv.ResourceAttributes.HOST_ID;
import static io.opentelemetry.semconv.ResourceAttributes.SERVICE_NAME;

@Configuration
@Profile("!test")
public class OpenTelemetryConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenTelemetryConfig.class);
    private static final String INSTRUMENTATION_NAME = OpenTelemetryConfig.class.getName();
    private final OpenTelemetry otel;
    private final Tracer tracer;
    private final io.opentelemetry.api.logs.Logger logger;

    @Value("${otel.exporter.otlp.logs.endpoint}")
    private String urlLogs;

    @Value("${otel.exporter.otlp.spans.endpoint}")
    private String urlSpans;
    
    @Value("${otel.exporter.otlp.metrics.endpoint}")
    private String urlMetrics;

    private OpenTelemetrySdk  instance;

    public OpenTelemetryConfig(OpenTelemetry openTelemetry) {
        this.otel = openTelemetry;
        this.tracer = openTelemetry.getTracer(INSTRUMENTATION_NAME);
        this.logger = openTelemetry.getLogsBridge().get("br.com.lue93.fx");
    }

    public OpenTelemetry autoconfiguredSdk() {
        Resource resource =
                Resource.getDefault().merge(
                        Resource.builder().put(SERVICE_NAME, "FX-OtlpExporter")
                                .build()
                );

        SdkLoggerProvider sdkLoggerProvider = SdkLoggerProvider.builder()
                .setResource(resource)
                .addLogRecordProcessor(
                        BatchLogRecordProcessor.builder(
                                OtlpHttpLogRecordExporter.builder()
                                        .setEndpoint(urlLogs)
                                        .build()
                        ).build()
                ).build();

        SdkTracerProvider sdkTracerProvider = SdkTracerProvider.builder()
                .setResource(resource)
                .addSpanProcessor(
                        BatchSpanProcessor.builder(
                                OtlpHttpSpanExporter
                                        .builder()
                                        .setEndpoint(urlSpans)
                                        .setTimeout(2, TimeUnit.SECONDS)
                                        .build()
                        ).setScheduleDelay(100, TimeUnit.MILLISECONDS).build()
                ).build();

        SdkMeterProvider sdkMeterProvider = SdkMeterProvider.builder()
                .setResource(resource)
                .registerMetricReader(
                        PeriodicMetricReader.builder(
                                        OtlpHttpMetricExporter
                                                .builder()
                                                .setEndpoint(urlMetrics)
                                                .build()
                                ).setInterval(Duration.ofMillis(10000))
                                .build())
                .build();

        OpenTelemetrySdk sdk =
                OpenTelemetrySdk.builder()
                        .setTracerProvider(sdkTracerProvider)
                        .setMeterProvider(sdkMeterProvider)
                        .setLoggerProvider(sdkLoggerProvider)
                        .buildAndRegisterGlobal();

        Runtime.getRuntime().addShutdownHook(new Thread(sdk::close));
        instance = sdk;

        return sdk;
    }

    public OpenTelemetrySdk getInstance() {
        return instance;
    }


    public SdkLoggerProvider getLoggerProvider() {
        return getInstance().getSdkLoggerProvider();
    }

}