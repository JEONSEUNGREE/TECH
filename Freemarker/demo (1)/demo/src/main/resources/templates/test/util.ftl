<#assign uri="enter_URI" />

<#function uri info>
    <#if (info?contains("/"))>
        info?replace("/", " ")
    </#if>
    <#return (info)>
</#function>