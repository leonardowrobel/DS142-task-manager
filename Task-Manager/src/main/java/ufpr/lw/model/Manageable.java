package ufpr.lw.model;

import java.util.UUID;

public abstract class Manageable {
    UUID id;
    Long createdAt;
    Long updatedAt;
    Long deletedAt = null;
}
