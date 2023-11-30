package ufpr.lw.model;

import java.time.Instant;
import java.util.UUID;

public abstract class Manageable {
    private UUID id;
    private Long createdAt;
    private Long updatedAt;

    public Manageable() {
        id = UUID.randomUUID();
        createdAt = Instant.now().getEpochSecond();
        updatedAt = Instant.now().getEpochSecond();
    }

    public UUID getId() {
        return id;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

}
