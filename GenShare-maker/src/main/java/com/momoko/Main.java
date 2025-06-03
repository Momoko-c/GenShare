package com.momoko;

import com.momoko.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws InterruptedException, TemplateException, IOException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}
