package com.redhat.demos.knative;

import java.util.UUID;

public record AffirmationJSON(UUID uuid, String text) {
}
