<#assign optTemp = .get_optional_template('some.ftl')>
<#if optTemp.exists>
    Template was found:
    <@optTemp.include/>
<#else>
    Template was missing.
</#if>
