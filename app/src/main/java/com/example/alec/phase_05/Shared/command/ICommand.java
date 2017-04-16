package com.example.alec.phase_05.Shared.command;

import java.io.Serializable;

/**
 * Contains an execute() method that returns a Result.
 * Created by samuel on 2/4/17.
 */
public interface ICommand extends Serializable
{
    /**
     * runs the command
     * @return the returned output of running this command
     */
    Result execute();
}
