package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.BaseCommand;

public class ParseCommand extends BaseCommand {
    String myCmd = "Parse";
    String myMsg;
    public ParseCommand(String _message) {
        super(_message);
        myMsg = new String (_message);
    }
    public String ExecuteMe(){
        String str = new String();
        try {
            Integer myInt = Integer.parseInt(myMsg);
            str = myInt.toString();
        }
        catch (NumberFormatException e){
            str = "Not a valid input";
        }
        return str;
    }
}
