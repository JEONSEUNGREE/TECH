<#function jsonP params>
    <div >
        ${params.name}
    </div>
    <div >
        ${params.description}
    </div>
    <#return (params)>
</#function>


<#function condition param>
    <#if param??>
        <div>
            ${param}
        </div>
    </#if>
    <#return (param)>
</#function>