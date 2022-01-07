<#function checkList param>
    <#if param??>
        ${param}
    <#else>
        <#assign param="">
    </#if>
    <#return (param)>
</#function>