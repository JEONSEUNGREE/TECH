<#macro testMacro>
    <br>
</#macro>

<#assign data = "TEST" />
${data}

${testMacro?is_macro}

