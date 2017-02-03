package com.example.alec.phase_05.Server;

import com.example.alec.phase_05.Shared.BaseCommand;
import com.example.alec.phase_05.Shared.IStringProcessor;
import com.example.alec.phase_05.Shared.Results;

/**
 * Created by Alec on 1/20/17.
 */
public class StringProcessor implements IStringProcessor {
    String myRtn;
    BaseCommand myCommand;

    public String getMyStr() {
        return myStr;
    }

    public void setMyStr(String myStr) {
        this.myStr = myStr;
    }

    public String getMyRtn() {
        return myRtn;
    }

    public void setMyRtn(String myRtn) {
        this.myRtn = myRtn;
    }

    public String getMyCmd() {
        return myCmd;
    }

    public void setMyCmd(String myCmd) {
        this.myCmd = myCmd;
    }

    String myStr;
    String myCmd;

    private static StringProcessor _instance;
    private static Results _results;

    public static StringProcessor getInstance(){
        if(_instance == null){
            _instance = new StringProcessor();
        }
        return _instance;
    }
    public StringProcessor(){}
    public StringProcessor(String str, String cmd){
        myRtn = new String();
        myStr = new String(str);
        myCmd = new String(cmd);
    }

    public String executeBaseCommand(BaseCommand myCommand,String command, String message){
        myRtn = new String();
        switch(myCmd){
            case "ToLowerCase":
                ToLowerCaseCommand newLowerCommand = new ToLowerCaseCommand(message);
                myRtn = newLowerCommand.ExecuteMe();
                break;
            case "Trim":
                TrimCommand newTrimCommand = new TrimCommand(message);
                myRtn = newTrimCommand.ExecuteMe();
                break;
            case "Parse":
                ParseCommand newParseCommand = new ParseCommand(message);
                myRtn = newParseCommand.ExecuteMe();
                break;
            default:
                myRtn = "Invalid Input";
                break;
        }
        return myRtn;
    }

    public String execute(){
        myRtn = new String();
        switch(myCmd){
            case "ToLowerCase":
                myRtn = toLowerCase(myStr);
                break;
            case "Trim":
                myRtn = trim(myStr);
                break;
            case "Parse":
                myRtn = parseInteger(myStr).toString();
                break;
            default:
                myRtn = "Invalid Input";
                break;
        }
        return myRtn;
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
