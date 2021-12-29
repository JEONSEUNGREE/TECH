<#--  <#macro parseDate src>
    <#if (src?length gt 8)>
        <#assign tmp="${src?replace(' ', '')}>
        ${tmp[0..4]?replace(".", "년")} ${tmp[5..7]?replace(".","월")} ${tmp[8..9]}일
    </#if>
</#macro>  -->

<#macro parseDate src>
    <#if (src?length gt 8)>
       <#--  변수 저장시 ""이 아닌 ''으로 초기화해야 된다.  -->
        <#assign temp="${src?replace(' ' ,'')}">
    </#if>
    <#if (temp?length gt 8)>
        ${temp[0..4]?replace(".","년")} ${temp[5..7]?replace(".","월")} ${temp[8..9]}일
    </#if>
</#macro>
<#--  Example  -->
<#--  <@parseDate src="199            5 .01 . 21" />  -->
  