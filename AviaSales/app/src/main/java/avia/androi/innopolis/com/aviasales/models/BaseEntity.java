package avia.androi.innopolis.com.aviasales.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class BaseEntity {

    @SerializedName("userId")
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
