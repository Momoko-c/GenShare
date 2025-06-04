package ${basePackage}.model;

import lombok.Data;

<#macro generateModel indent modelInfo>
<#if modelInfo.description??>
${indent}/**
${indent} * ${modelInfo.description}
${indent} */
</#if>
<#if modelInfo.type?? && modelInfo.fieldName??>
${indent}public ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#if>
</#macro>

/**
* 数据模型
*/
@Data
public class DataModel {
<#list modelConfig.models as modelInfo>

    <#-- 有分组 -->
    <#if modelInfo.groupKey??>
    <#if modelInfo.groupName??>
    /**
    * ${modelInfo.groupName}
    */
    </#if>
    <#if modelInfo.type?? && modelInfo.groupKey??>
    public ${modelInfo.type} ${modelInfo.groupKey} = new ${modelInfo.type}();
    </#if>

    <#if modelInfo.description??>
    /**
    * ${modelInfo.description}
    */
    </#if>
    <#if modelInfo.type??>
    @Data
    public static class ${modelInfo.type} {
    <#list modelInfo.models as modelInfo>
        <@generateModel indent="        " modelInfo=modelInfo />
    </#list>
    }
    </#if>

    <#else>
    <#-- 无分组 -->
    <@generateModel indent="    " modelInfo=modelInfo />
    </#if>
</#list>
}
