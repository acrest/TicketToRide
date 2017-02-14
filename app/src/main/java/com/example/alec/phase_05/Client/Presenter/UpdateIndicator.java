package com.example.alec.phase_05.Client.Presenter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by samuel on 2/11/17.
 */

public class UpdateIndicator {
    private Set<String> properties;

    public UpdateIndicator() {
        properties = new HashSet<>();
    }

    public void addProperty(String property){
        properties.add(property);
    }

    public boolean needUpdate(String property) {
        return properties.contains(property);
    }
}
