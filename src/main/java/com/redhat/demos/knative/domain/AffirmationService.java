package com.redhat.demos.knative.domain;

import com.redhat.demos.knative.api.AffirmationJSON;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AffirmationService {

    @Inject
    AffirmationRepository affirmationRepository;

    public AffirmationJSON getRandomAffirmation() {
        Affirmation affirmation = affirmationRepository.randomAffirmation();
        Log.debugf("getRandomAffirmation: %s", affirmation);
        return affirmation.toJson();
    }
}
