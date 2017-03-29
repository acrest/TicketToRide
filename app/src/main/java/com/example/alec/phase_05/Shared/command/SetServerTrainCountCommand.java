package com.example.alec.phase_05.Shared.command;

/**
 * Created by samuel on 3/29/17.
 */

public abstract class SetServerTrainCountCommand extends BaseCommand {
    private int count;

    public SetServerTrainCountCommand(int count) {
        super("SetServerTrainCount");
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
