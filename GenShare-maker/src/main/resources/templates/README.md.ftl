# ${name}

> ${description}
>
> 作者：${author}
>
> 基于 程序员鱼皮的 [鱼籽代码生成器项目](https://github.com/liyupi/yuzi-generator) 制作，感谢您的使用！

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：

```
generator <命令> <选项参数>
```

示例命令：

```
generator generate <#list modelConfig.models as modelInfo><#if modelInfo.abbr??>-${modelInfo.abbr} </#if></#list>
```

### 参数说明

<#list modelConfig.models as modelInfo>
<#if modelInfo.fieldName??>
${modelInfo?index + 1}）${modelInfo.fieldName}

<#if modelInfo.type??>类型：${modelInfo.type}</#if>

<#if modelInfo.description??>描述：${modelInfo.description}</#if>

<#if modelInfo.defaultValue??>默认值：${modelInfo.defaultValue?c}</#if>

<#if modelInfo.abbr??>缩写： -${modelInfo.abbr}</#if>

</#if>
</#list>


## 🛠️ 技术栈

| 技术 | 版本 | 用途 |
|------|------|------|
| Java | 8+ | 核心开发语言 |
| Maven | 3.6+ | 项目管理和构建 |
| FreeMarker | 2.3.32 | 模板引擎 |
| Picocli | 4.7.5 | 命令行接口框架 |
| Hutool | 5.8.37 | Java 工具库 |
| Lombok | 1.18.30 | 简化 Java 代码 |

---
⭐ 如果这个项目对您有帮助，请给它一个星标！