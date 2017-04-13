package com.example.alec.phase_05.Server.Database;

import java.io.File;

/**
 * Created by samuel on 4/13/17.
 */

public final class FileUtility {
    public static void recursiveDelete(File root) {
        if (root.isDirectory()) {
            for (File child : root.listFiles()) {
                recursiveDelete(child);
            }
        }
        root.delete();
    }

    private FileUtility() {
    }
}
