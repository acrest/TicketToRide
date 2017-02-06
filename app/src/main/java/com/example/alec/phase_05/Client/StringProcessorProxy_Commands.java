package com.example.alec.phase_05.Client;

import com.example.alec.phase_05.Server.ParseCommand;
import com.example.alec.phase_05.Server.ToLowerCaseCommand;
import com.example.alec.phase_05.Server.TrimCommand;
import com.example.alec.phase_05.Shared.BaseCommand;
import com.example.alec.phase_05.Shared.IStringProcessor;

public class StringProcessorProxy_Commands implements IStringProcessor {
    String myRtn;
    String myCmd;
    String myHost;
    String myPort;
    String myStr;
    BaseCommand baseCMD;
    ClientCommunicator myCC;
    public StringProcessorProxy_Commands(String str, String cmd, ClientCommunicator cc, String serverHost,String serverPort){
        myRtn = new String();
        myCmd = new String(cmd);
        myHost = new String(serverHost);
        myPort = new String(serverPort);
        baseCMD = new BaseCommand(str);
        myStr = new String(str);
        myCC = cc;
        execute();
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
        baseCMD = new ToLowerCaseCommand(myStr);
        ClientCommunicator myInstance = myCC.get_instance();
        myInstance.toExecCommand(myHost,myPort,baseCMD,"/exec");
        return myStr;
    }

    @Override
    public String trim(String s){
        baseCMD = new TrimCommand(myStr);
        ClientCommunicator myInstance = myCC.get_instance();
        myInstance.toExecCommand(myHost,myPort,baseCMD,"/exec");
        return myStr;
    }

    @Override
    public String parseInteger(String s){
        baseCMD = new ParseCommand(myStr);
        ClientCommunicator myInstance = myCC.get_instance();
        myInstance.toExecCommand(myHost,myPort,baseCMD,"/exec");
        return myStr;
    }
}
