package com.igortullio.server.adapter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private IntegrationProperties integration;

    public IntegrationProperties getIntegration() {
        return integration;
    }

    public void setIntegration(IntegrationProperties integration) {
        this.integration = integration;
    }

    public static class IntegrationProperties {

        private IntegrationConfig wiremock;

        public IntegrationConfig getWiremock() {
            return wiremock;
        }

        public void setWiremock(IntegrationConfig wiremock) {
            this.wiremock = wiremock;
        }

        public static class IntegrationConfig {
            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }


    }

}
