package com.momoko;

import com.momoko.maker.cli.CommandExecutor;

//public class Main {
//
//    public static void main(String[] args) {
//        // args = new String[] { "list" };
//        // args = new String[] { "config" };
//        args = new String[] { "generate","-l","-a","-o" };
//        CommandExecutor commandExecutor = new CommandExecutor();
//        commandExecutor.doExecute(args);
//    }
//}

public class Main {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}
