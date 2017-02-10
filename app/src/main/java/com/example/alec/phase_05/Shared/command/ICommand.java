package com.example.alec.phase_05.Shared.command;

/**
 * Contains an execute() method that returns a Result.
 * Created by samuel on 2/4/17.
 */
public interface ICommand
{
    /**
     * runs the command
     * @return the returned output of running this command
     */
    Result execute();
}
