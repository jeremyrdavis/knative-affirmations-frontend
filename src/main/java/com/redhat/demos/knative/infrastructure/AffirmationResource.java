package com.redhat.demos.knative.infrastructure;

import com.redhat.demos.knative.api.AffirmationJSON;
import com.redhat.demos.knative.domain.AffirmationService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.*;

@Path("/api/affirmation")
public class AffirmationResource {

    private final Map<UUID, AffirmationJSON> affirmations = new HashMap<>();
    private final Random random = new Random();

    @Inject
    AffirmationService affirmationService;

    @GET
    @Path("/random")
    public AffirmationJSON getRandomAffirmation() {
        AffirmationJSON affirmationJSON = affirmationService.getRandomAffirmation();
        Log.debugf("getRandomAffirmation: %s", affirmationJSON);
        return affirmationJSON;
    }
}
