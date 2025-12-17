package br.com.lue93.fx.infra.spring.micrometer;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
public class MicrometerConfig {
    @Value("${otel.exporter.otlp.metrics.endpoint}")
    private String otlpMetricsEndpoint;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags(Environment env) {
        String appName = env.getProperty("management.otlp.metrics.application.name", "fx");
        return otlpMeterRegistry -> otlpMeterRegistry.config().commonTags("application", appName);
    }
}