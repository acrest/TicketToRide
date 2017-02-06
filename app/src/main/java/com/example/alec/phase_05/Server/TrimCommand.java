package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.BaseCommand;

public class TrimCommand extends BaseCommand {
    String myCmd = "Trim";
    String myMsg;
    public TrimCommand(String _message) {
        super(_message);
        myMsg = new String (_message);
    }
    public String ExecuteMe(){
        String Str = new String(myMsg.trim());
        return Str;
    }
}
