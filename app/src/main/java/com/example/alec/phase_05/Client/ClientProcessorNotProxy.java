package com.example.alec.phase_05.Client;
import com.example.alec.phase_05.Shared.IStringProcessor;

public class ClientProcessorNotProxy implements IStringProcessor {
    String myRtn;
    String myStr;
    String myCmd;
    public ClientProcessorNotProxy(String str, String cmd){
        myRtn = new String();
        myStr = new String(str);
        myCmd = new String(cmd);
    }

    public void execute(){
        myRtn = new String();
        switch(myCmd){
            case "L":
                myRtn = toLowerCase(myStr);
                break;
            case "T":
                myRtn = trim(myStr);
                break;
            case "P":
                myRtn = parseInteger(myStr).toString();
                break;
            default:
                myRtn = "Invalid Input";
                break;
        }
        System.out.print("Result after execution is:\n"+myRtn);
    }

    @Override
    public String toLowerCase(String s) {
        String Str = new String(s.toLowerCase());
        return Str;
    }

    @Override
    public String trim(String s){
        String Str = new String(s.trim());
        return Str;
    }

    @Override
    public String parseInteger(String s){
        String str = new String();
        try {
            Integer myInt = Integer.parseInt(s);
            str = myInt.toString();
        }
        catch (NumberFormatException e){
            str = "Not a valid input";
        }
        return str;
    }
}
