package com.example.alec.phase_05.Server.command_line;

import com.example.alec.phase_05.Client.Model.ClientModel;
import com.example.alec.phase_05.Server.model.ServerFacade;
import com.example.alec.phase_05.Server.model.ServerModel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

/**
 * Created by samuel on 3/24/17.
 */

public class ServerCommandLine {
    private Scanner scanner;

    public ServerCommandLine() {
        scanner = null;
    }

    public void start() {
        scanner = new Scanner(System.in);
        while (true) {
            out.println("1 - ServerModel");
            out.println("2 - ServerFacade");
            out.print("Choose a singleton to examine: ");
            if(!scanner.hasNextInt()) {
                out.println("Must input an int");
            } else {
                int choice = scanner.nextInt();
                switch(choice) {
                    case 1:
                        examineObject(ServerModel.getInstance());
                        break;
                    case 2:
                        examineObject(ServerFacade.getInstance());
                        break;
                    default:
                        out.println("Invalid choice");
                }
            }
        }
    }

    private boolean shouldExamine(Class<?> type) {
        return !(type.equals(int.class) || type.equals(String.class) || type.equals(boolean.class));
    }

    private void examineObject(Object object) {
        boolean finished = false;
        Class<?> type = object.getClass();

        while (!finished) {
            printOptions(type);

            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        printMethods(type);
                        break;
                    case 2:
                        printFields(type, object);
                        break;
                    case 3:
                        finished = true;
                        break;
                    default:
                        out.println("Invalid number");
                }
            } else {
                String methodName = scanner.next();
                Scanner argScanner = new Scanner(scanner.nextLine());
                List<String> args = new ArrayList<>();
                while (argScanner.hasNext()) {
                    args.add(argScanner.next());
                }
                Method method = findMethod(type.getMethods(), methodName);
                if (method == null) {
                    out.println("Could not find method \"" + methodName + "\"");
                } else {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != args.size()) {
                        out.println("Invalid arity for method \"" + methodName + "\"");
                    } else {
                        Object returnValue = null;
                        try {
                            returnValue = method.invoke(object, parseArgs(parameterTypes, args));
                        } catch (IllegalAccessException x) {
                            out.println("IllegalAccessException: " + x.getMessage());
                        } catch (InvocationTargetException x) {
                            out.println("InvocationTargetException: " + x.getMessage());
                        }
                        out.println(returnValue);
                        if (returnValue != null && shouldExamine(method.getReturnType())) {
                            examineObject(object);
                        }
                    }
                }
            }
        }
    }

    private Object parseArg(Class<?> type, String value) {
        if (type.equals(int.class)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException x) {
                out.println("Unable to parse int");
            }
        } else if (type.equals(String.class)) {
            return value;
        }
        return null;
    }

    private Object[] parseArgs(Class<?>[] argTypes, List<String> argValues) {
        Object[] objects = new Object[argTypes.length];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = parseArg(argTypes[i], argValues.get(i));
        }
        return objects;
    }

    private Method findMethod(Method[] methods, String name) {
        for (Method method : methods) {
            if (method.getName().equals(name)) {
                return method;
            }
        }
        return null;
    }

    private void printOptions(Class<?> type) {
        out.println("Examining object of type \"" + type.getSimpleName() + "\"");
        out.println("1 - print methods");
        out.println("2 - print fields");
        out.println("3 - finish examination");
        out.print("Number or method call: ");
    }

    private void printMethods(Class<?> type) {
        for (Method method : type.getMethods()) {
            out.print(method.getName() + "(");
            for (Class<?> parameterType : method.getParameterTypes()) {
                out.print(parameterType.getSimpleName() + ", ");
            }
            out.println(")");
        }
    }

    private void printFields(Class<?> type, Object object) {
        for (Field field : type.getFields()) {
            out.print(field + " - ");
            try {
                out.println(field.get(object));
            } catch (IllegalAccessException x) {
                out.println("cannot access");
            }
        }
    }
}
