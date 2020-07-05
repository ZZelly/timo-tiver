package org.timo.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import java.util.UUID;

public abstract class BaseEntity {

    @PrimaryKey
    private UUID id;

    protected BaseEntity() {}
    protected BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }
}
