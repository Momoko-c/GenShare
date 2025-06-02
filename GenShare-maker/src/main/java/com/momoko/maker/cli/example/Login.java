package com.momoko.maker.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import javax.swing.plaf.basic.BasicListUI;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.Callable;

// 可选交互式（arity）
public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name")
    String user;

    // 设置了 arity 参数，可选交互式
    // arity = "0..1"：意思是该参数值可以：
    // 有值（-p 123）
    // 没值（-p，这时会触发输入提示）
    // 不出现（没有-p），password默认为null，不触发提示 [需修改，避免必要参数出现null]

    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Passphrase", interactive = true)
    String password;

    // 设置了 arity 参数，可选交互式
    @Option(names = {"-cp", "--checkPassword"}, arity = "0..1", description = "Check Password", interactive = true)
    String checkPassword;

    @Override
    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }


    public static void main(String[] args) {
        // 构造初始化列表
        List<String> argList = new ArrayList<>(Arrays.asList(args));
        Set<String> argSet = new HashSet<>(argList);

        // 获取所有 interactive=true 的选项名
        List<String> missingInteractiveOptions = new ArrayList<>();
        for (Field field : Login.class.getDeclaredFields()) {
            Option option = field.getAnnotation(Option.class);
            if (option != null && option.interactive()) {
                boolean found = false;
                for (String name : option.names()) {
                    if (argSet.contains(name)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    // 选择第一个作为默认交互触发项
                    missingInteractiveOptions.add(option.names()[0]);
                }
            }
        }
        // 添加缺失的交互选项名
        argList.addAll(missingInteractiveOptions);

        // 执行命令
        // new CommandLine(new Login()).execute(argList.toArray(new String[0]));
        new CommandLine(new Login()).execute("-u", "user","-p", "password");
    }
}


