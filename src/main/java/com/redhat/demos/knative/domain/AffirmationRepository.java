package com.redhat.demos.knative.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AffirmationRepository implements PanacheRepository<AffirmationEntity> {

    protected Affirmation randomAffirmation() {
        long count = count();
        int index = (int)(Math.random() * count);
        AffirmationEntity entity = findAll().page(index, 1).firstResult();
        Affirmation affirmation = Affirmation.fromEntity(entity);
        return affirmation;
    }
}
