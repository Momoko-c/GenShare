package ${basePackage}.model;

import lombok.Data;

/**
* 数据模型
*/
@Data
public class DataModel {
<#list modelConfig.models as modelInfo>

    <#if modelInfo.description??>
        /**
        * ${modelInfo.description}
        */
    </#if>
    // modelInfo.defaultValue?c的作用是将任何类型的变量转换为字符串
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;
</#list>
}
