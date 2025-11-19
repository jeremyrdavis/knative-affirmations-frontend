package com.redhat.demos.knative;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.*;

@Path("/api/affirmation")
public class AffirmationResource {

    private final Map<UUID, AffirmationJSON> affirmations = new HashMap<>();
    private final Random random = new Random();

    public AffirmationResource() {
        // Populate the map with 5 random AffirmationJSON records
        UUID id1 = UUID.randomUUID(); affirmations.put(id1, new AffirmationJSON(id1, "You are doing great!"));
        UUID id2 = UUID.randomUUID(); affirmations.put(id2, new AffirmationJSON(id2, "Keep pushing forward."));
        UUID id3 = UUID.randomUUID(); affirmations.put(id3, new AffirmationJSON(id3, "Believe in yourself."));
        UUID id4 = UUID.randomUUID(); affirmations.put(id4, new AffirmationJSON(id4, "Every day is a new opportunity."));
        UUID id5 = UUID.randomUUID(); affirmations.put(id5, new AffirmationJSON(id5, "You make a difference."));
    }

    @GET
    @Path("/random")
    public AffirmationJSON getRandomAffirmation() {
        List<AffirmationJSON> list = new ArrayList<>(affirmations.values());
        return list.get(random.nextInt(list.size()));    }
}
