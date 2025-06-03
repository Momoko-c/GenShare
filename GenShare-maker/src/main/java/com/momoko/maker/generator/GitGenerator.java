package com.momoko.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import com.momoko.maker.meta.Meta;

import java.io.File;
import java.io.IOException;

/**
 * Git 仓库生成器
 * 负责初始化 Git 仓库和复制 .gitignore 文件
 */
public class GitGenerator {

    /**
     * 生成 Git 仓库
     * @param outputPath 输出路径
     * @param gitConfig Git配置
     * @throws IOException
     * @throws InterruptedException
     */
    public static void doGenerate(String outputPath, Meta.GitConfig gitConfig) throws IOException, InterruptedException {
        if (gitConfig == null || !gitConfig.isEnableGit()) {
            return;
        }

        // 初始化 Git 仓库
        initGitRepository(outputPath);

        // 复制 .gitignore 文件
        if (gitConfig.isEnableGitignore()) {
            copyGitignoreFile(outputPath);
        }

        // 不再自动执行初始提交，让用户手动控制
        System.out.println("Git 仓库初始化完成，您可以手动执行 git add 和 git commit 来提交代码");
    }

    /**
     * 初始化 Git 仓库
     * @param outputPath 输出路径
     * @throws IOException
     * @throws InterruptedException
     */
    private static void initGitRepository(String outputPath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("git", "init");
        processBuilder.directory(new File(outputPath));
        
        Process process = processBuilder.start();
        int exitCode = process.waitFor();
        
        if (exitCode == 0) {
            System.out.println("Git 仓库初始化成功: " + outputPath);
        } else {
            System.err.println("Git 仓库初始化失败，退出码: " + exitCode);
            throw new RuntimeException("Git 初始化失败");
        }
    }

    /**
     * 复制 .gitignore 文件
     * @param outputPath 输出路径
     */
    private static void copyGitignoreFile(String outputPath) {
        try {
            ClassPathResource resource = new ClassPathResource("templates/.gitignore");
            String gitignoreContent = resource.readUtf8Str();
            
            String gitignoreFilePath = outputPath + File.separator + ".gitignore";
            FileUtil.writeUtf8String(gitignoreContent, gitignoreFilePath);
            
            System.out.println(".gitignore 文件生成成功: " + gitignoreFilePath);
        } catch (Exception e) {
            System.err.println(".gitignore 文件生成失败: " + e.getMessage());
            // 如果模板文件不存在，使用默认的 Java .gitignore 内容
            createDefaultGitignore(outputPath);
        }
    }

    /**
     * 创建默认的 Java .gitignore 文件
     * @param outputPath 输出路径
     */
    private static void createDefaultGitignore(String outputPath) {
        String defaultGitignoreContent = getDefaultJavaGitignoreContent();
        String gitignoreFilePath = outputPath + File.separator + ".gitignore";
        FileUtil.writeUtf8String(defaultGitignoreContent, gitignoreFilePath);
        System.out.println("使用默认 .gitignore 模板生成成功: " + gitignoreFilePath);
    }

    /**
     * 获取默认的 Java .gitignore 内容
     * @return gitignore 内容
     */
    private static String getDefaultJavaGitignoreContent() {
        return "# Compiled class file\n" +
                "*.class\n" +
                "\n" +
                "# Log file\n" +
                "*.log\n" +
                "\n" +
                "# BlueJ files\n" +
                "*.ctxt\n" +
                "\n" +
                "# Mobile Tools for Java (J2ME)\n" +
                ".mtj.tmp/\n" +
                "\n" +
                "# Package Files #\n" +
                "*.jar\n" +
                "*.war\n" +
                "*.nar\n" +
                "*.ear\n" +
                "*.zip\n" +
                "*.tar.gz\n" +
                "*.rar\n" +
                "\n" +
                "# virtual machine crash logs\n" +
                "hs_err_pid*\n" +
                "replay_pid*\n" +
                "\n" +
                "# Maven\n" +
                "target/\n" +
                "pom.xml.tag\n" +
                "pom.xml.releaseBackup\n" +
                "pom.xml.versionsBackup\n" +
                "pom.xml.next\n" +
                "release.properties\n" +
                "dependency-reduced-pom.xml\n" +
                "buildNumber.properties\n" +
                ".mvn/timing.properties\n" +
                ".mvn/wrapper/maven-wrapper.jar\n" +
                "\n" +
                "# Gradle\n" +
                ".gradle\n" +
                "**/build/\n" +
                "!src/**/build/\n" +
                "gradle-app.setting\n" +
                "!gradle-wrapper.jar\n" +
                "!gradle-wrapper.properties\n" +
                "\n" +
                "# IntelliJ IDEA\n" +
                ".idea/\n" +
                "*.iws\n" +
                "*.iml\n" +
                "*.ipr\n" +
                "out/\n" +
                "\n" +
                "# Eclipse\n" +
                ".apt_generated\n" +
                ".classpath\n" +
                ".factorypath\n" +
                ".project\n" +
                ".settings\n" +
                ".springBeans\n" +
                ".sts4-cache\n" +
                "bin/\n" +
                "\n" +
                "# Visual Studio Code\n" +
                ".vscode/\n" +
                "\n" +
                "# OS generated files\n" +
                ".DS_Store\n" +
                ".DS_Store?\n" +
                "._*\n" +
                ".Spotlight-V100\n" +
                ".Trashes\n" +
                "ehthumbs.db\n" +
                "Thumbs.db\n" +
                "\n" +
                "# Generated files\n" +
                "generated/\n";
    }
} 