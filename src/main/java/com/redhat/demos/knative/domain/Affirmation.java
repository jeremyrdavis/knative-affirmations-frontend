package com.redhat.demos.knative.domain;

import com.redhat.demos.knative.api.AffirmationJSON;

import java.util.UUID;

public class Affirmation {

    UUID uuid;

    Long primaryKey;

    String text;

    Integer thumbsUp;

    Integer thumbsDown;

    protected static Affirmation fromEntity(AffirmationEntity entity) {
        Affirmation affirmation = new Affirmation();
        affirmation.uuid = entity.uuid;
        affirmation.primaryKey = entity.id;
        affirmation.text = entity.text;
        affirmation.thumbsUp = entity.thumbsUp;
        affirmation.thumbsDown =  entity.thumbsDown;
        return affirmation;
    }

    protected static Affirmation createNewAffirmation(String text) {
        Affirmation affirmation = new Affirmation();
        affirmation.uuid = UUID.randomUUID();
        affirmation.text = text;
        affirmation.thumbsUp = 0;
        affirmation.thumbsDown = 0;
        return affirmation;
    }

    public AffirmationJSON toJson() {
        return new AffirmationJSON(uuid, text);
    }

    AffirmationEntity toEntity(String text) {
        return new AffirmationEntity(primaryKey, uuid, text, thumbsUp, thumbsDown);
    }
}
