package com.example.paniermobil.monCash.consomer;

import com.google.gson.GsonBuilder;

public class Model {
    public String toJSON() {
        return new GsonBuilder().setPrettyPrinting().create().toJson(this);
    }

    @Override
    public String toString() {
        return toJSON();
    }
}
