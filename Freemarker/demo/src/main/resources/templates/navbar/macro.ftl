<#macro macro3 a b ext...> a = ${a} b = ${b} 
    <#if ext?is_sequence>
    <#list ext as e> ${e?index} = ${e}! 
    </#list> 
    <#else> 
    <#list ext as k, v> ${k} = ${v}? 
    </#list> 
    </#if> 
</#macro>
