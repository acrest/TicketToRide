package com.example.alec.phase_05.Shared.model;

import java.util.Collection;
import java.util.Set;

/**
 * Created by samuel on 2/22/17.
 */

public interface IPlayerColorManager {

    String getColor(Player player);
    void setColor(Player player, String color);
    Collection<String> getUsedColors();
}
