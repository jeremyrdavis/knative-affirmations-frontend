package com.redhat.demos.knative;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/config.js")
public class ConfigScriptResource {

    @Inject
    @Location("config.txt")
    Template config; // Maps to src/main/resources/templates/config.txt

    @ConfigProperty(name = "affirmations.api.url")
    String apiBaseUrl;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String get() {
        return config.data("apiBaseUrl", apiBaseUrl).render();
    }
}
