# 🚀 GenShare-Maker

![Java](https://img.shields.io/badge/Java-8+-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-green.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

> 一个基于 FreeMarker 模板引擎的代码生成器框架，能够根据模板文件和元数据配置自动生成完整的 Java 项目代码。

## ✨ 功能特性

- 🎯 **模板驱动**：基于 FreeMarker 模板引擎，支持灵活的代码模板定制
- 🔧 **命令行工具**：提供完整的 CLI 工具，支持多种生成命令
- 📦 **一键打包**：自动生成可执行 JAR 包和脚本文件
- 🎨 **高度可配置**：通过 meta.json 配置文件灵活定制生成内容
- 🔗 **Git 集成**：可选的 Git 仓库初始化和 .gitignore 生成

## 🚀 快速开始

### 前置要求

- JDK 8 或更高版本
- Maven 3.6 或更高版本

### 使用步骤

1. **克隆并构建项目**
   ```bash
   git clone https://github.com/Momoko-c/GenShare.git
   cd GenShare-maker
   mvn clean compile
   ```

2. **运行主生成器**
   ```bash
   # 在项目根目录下运行 Main 函数
   mvn exec:java -Dexec.mainClass="com.momoko.maker.Main"
   ```
   此步骤会在 `generated/acm-template-pro-generator` 目录下生成完整的代码生成器。

3. **使用生成的代码生成器**
   ```bash
   # 进入生成的项目目录
   cd generated/acm-template-pro-generator
   
   # 使用生成的脚本文件
   # Windows 下：
   .\generator generate -a -l -o
   
   # Linux/Mac 下：
   ./generator generate -a -l -o
   ```

4. **查看生成结果**
   生成的代码文件将输出到 `generated-code` 目录下。

## 🎯 命令行参数

| 参数 | 全称 | 说明 |
|------|------|------|
| `-a` | `--author` | 作者注释 |
| `-l` | `--loop` | 是否生成循环代码 |
| `-o` | `--outputText` | 输出信息 |

## 🔧 配置说明

项目通过 `src/main/resources/meta.json` 文件进行配置：

```json
{
  "name": "项目名称",
  "description": "项目描述", 
  "basePackage": "Java基础包名",
  "fileConfig": {
    "inputRootPath": "模板文件路径",
    "outputRootPath": "生成代码输出路径"
  },
  "gitConfig": {
    "enableGit": true,        // 是否初始化Git仓库
    "enableGitignore": true   // 是否生成.gitignore文件
  }
}
```

## 📁 核心目录结构

```
GenShare-maker/
├── src/main/java/com/momoko/maker/
│   ├── Main.java                    # 主入口类
│   ├── generator/                   # 代码生成器
│   │   ├── main/                    # 主生成器
│   │   ├── GitGenerator.java        # Git仓库生成器
│   │   └── JarGenerator.java        # JAR包生成器
│   └── meta/                        # 元数据管理
├── src/main/resources/
│   ├── meta.json                    # 配置文件
│   └── templates/                   # 模板文件
└── generated/                       # 生成输出目录
```

## 📝 许可证

本项目采用 MIT 许可证。

---

⭐ 如果这个项目对您有帮助，请给它一个星标！