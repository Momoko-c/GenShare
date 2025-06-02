package com.momoko.generator;

import com.momoko.model.MainTemplateConfig;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 动态文件生成
 */
public class DynamicGenerator {

//    public static void main(String[] args) throws IOException, TemplateException {
//        // new 出 Configuration 对象，参数为 FreeMarker 版本号
//        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
//
//        // 指定模板文件所在的路径
//        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
//
//        // 设置模板文件使用的字符集
//        configuration.setDefaultEncoding("utf-8");
//
//        // 创建模板对象，加载指定模板
//        Template template = configuration.getTemplate("MainTemplate.java.ftl");
//
//        // 创建数据模型
//        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
//        mainTemplateConfig.setAuthor("momoko");
//        // 不使用循环
//        mainTemplateConfig.setLoop(false);
//        mainTemplateConfig.setOutputText("求和结果：");
//
//        // 生成
//        Writer out = new FileWriter("MainTemplate.java");
//        template.process(mainTemplateConfig, out);
//
//        // 生成文件后别忘了关闭哦
//        out.close();
//    }
public static void main(String[] args) throws IOException, TemplateException {
    String projectPath = System.getProperty("user.dir");
    System.out.println(projectPath);
    // String inputPath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
    String inputPath = Paths.get(projectPath, "src", "main", "resources", "templates", "MainTemplate.java.ftl").toString();
    // String outputPath = projectPath + File.separator + "MainTemplate.java";
    String outputPath = Paths.get(projectPath,"MainTemplate.java").toString();
    MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
    mainTemplateConfig.setAuthor("momoko");
    mainTemplateConfig.setLoop(false);
    mainTemplateConfig.setOutputText("求和结果：");
    doGenerate(inputPath, outputPath, mainTemplateConfig);
}


    /**
     * 生成文件
     *
     * @param inputPath 模板文件输入路径
     * @param outputPath 输出路径
     * @param model 数据模型
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath, String outputPath, Object model) throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        File templateDir = new File(inputPath).getParentFile();
        configuration.setDirectoryForTemplateLoading(templateDir);

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);

        // 生成
        Writer out = new OutputStreamWriter(Files.newOutputStream(Paths.get(outputPath)), "utf-8");
        template.process(model, out);

        // 生成文件后别忘了关闭哦
        out.close();
    }


}

