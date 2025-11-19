package com.redhat.demos.knative.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
public class AffirmationEntity extends PanacheEntity {

    UUID uuid;

    String text;

    Integer thumbsUp;

    Integer thumbsDown;

    protected AffirmationEntity() {
    }

    AffirmationEntity(Long id, UUID uuid, String text, Integer thumbsUp, Integer thumbsDown) {
        this.id = id;
        this.uuid = uuid;
        this.text = text;
        this.thumbsUp = thumbsUp;
        this.thumbsDown = thumbsDown;
    }

    UUID getUuid() {
        return uuid;
    }

    String getText() {
        return text;
    }

    Integer getThumbsUp() {
        return thumbsUp;
    }

    Integer getThumbsDown() {
        return thumbsDown;
    }
}
