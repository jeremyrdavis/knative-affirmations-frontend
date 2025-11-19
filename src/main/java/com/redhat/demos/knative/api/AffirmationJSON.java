package com.redhat.demos.knative.api;

import java.util.UUID;

public record AffirmationJSON(UUID uuid, String text) {
}
