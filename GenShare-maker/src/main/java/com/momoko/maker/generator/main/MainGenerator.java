package com.momoko.maker.generator.main;

/**
 * 生成代码生成器
 * 不需要精简版
 */
public class MainGenerator extends GenerateTemplate {

    @Override
    protected void buildDist(String outputPath, String sourceCopyDestPath, String jarPath, String shellOutputFilePath) {
        System.out.println("不输出 dist ！");
    }
}
