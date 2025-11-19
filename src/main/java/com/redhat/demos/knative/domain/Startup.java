package com.redhat.demos.knative.domain;

import io.quarkus.logging.Log;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Singleton
public class Startup {

    private final Random random = new Random();

    @Transactional
    public void onStartup(@Observes StartupEvent startupEvent) {
        Log.debugf("onStartup: seeding affirmations");

        List<String> samples = Arrays.asList(
                "You are doing great!",
                "Keep pushing forward.",
                "Believe in yourself.",
                "Every day is a new opportunity.",
                "You make a difference.",
                "Small steps lead to big changes.",
                "You are capable of amazing things."
        );

        for (int i = 0; i < 5; i++) {
            String text = samples.get(random.nextInt(samples.size()));
            Affirmation affirmation =  Affirmation.createNewAffirmation(text);
            AffirmationEntity entity = affirmation.toEntity(text);
            entity.persist();
            Log.debugf("persisted affirmation %s", entity.id);
        }
    }}
