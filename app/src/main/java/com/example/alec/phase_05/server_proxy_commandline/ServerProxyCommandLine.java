package com.example.alec.phase_05.server_proxy_commandline;

import com.example.alec.phase_05.Client.ServerProxy;
import com.example.alec.phase_05.Shared.model.IServer;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by samuel on 3/20/17.
 */

public class ServerProxyCommandLine {
    public static void main(String [] args){
        ServerProxyCommandLine commandLine = new ServerProxyCommandLine();
        commandLine.start();
    }

    private IServer proxy;
    private Scanner scanner;

    private ServerProxyCommandLine() {
        proxy = ServerProxy.getInstance();
        scanner = new Scanner(System.in);
    }

    private void start() {
        while(true) {
            Method method = findMethodByName(prompt("Method Name"));
            if(method == null) {
                System.out.println("method not found");
                continue;
            }
            Class<?>[] types = method.getParameterTypes();
            try {
                System.out.println(Arrays.toString(types));
                System.out.println(method.invoke(proxy, parseArgs(promptArgs(types.length), types)));
            } catch(Exception e) {
                System.out.println("Unable to invoke method");
                e.printStackTrace();
            }
        }
    }

    private Object parseArg(String value, Class<?> type) {
        if(type.equals(String.class)) {
            return value;
        }
        if(type.equals(int.class)) {
            return Integer.parseInt(value);
        }
        return null;
    }

    private Object[] parseArgs(String[] values, Class<?>[] types){
        Object[] args = new Object[values.length];
        for(int i = 0; i < args.length; i++) {
            args[i] = parseArg(values[i], types[i]);
        }
        return args;
    }

    private Method findMethodByName(String name) {
        for(Method method : proxy.getClass().getMethods()) {
            if(method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    private String prompt(String value) {
        System.out.print(value + ": ");
        return scanner.nextLine();
    }

    private String[] promptArgs(int count) {
        String[] answers = new String[count];
        for(int i = 0; i < answers.length; i++) {
            answers[i] = prompt("arg" + i);
        }
        return answers;
    }
}
