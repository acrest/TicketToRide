package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.BaseCommand;

/**
 * Created by Alec on 1/22/17.
 */
public class ToLowerCaseCommand extends BaseCommand{
    String myCmd = "ToLowerCase";
    String myMsg;
    public ToLowerCaseCommand(String _message) {
        super(_message);
        myMsg = new String (_message);
    }
    public String ExecuteMe(){
        String Str = new String(myMsg.toLowerCase());
        return Str;
    }



}
