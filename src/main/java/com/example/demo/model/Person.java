package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class Person {
    private final UUID id;
    @NotBlank
    private String name;

    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name")   String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

}
