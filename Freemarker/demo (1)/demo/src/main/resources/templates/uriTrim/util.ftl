<#import "const.ftl" as const />

<#macro header >
    <h2>프리마커 Uri</h2>
</#macro>

<#--  uri / 단위 별로 자르기  -->
<#function mainPageCutter info count>
    <#if info?starts_with(first) >
        <#assign x = info?replace(first, "") />
        <#assign x = x?keep_before("/") />
    </#if>
    <#if (count > 1)>
        <#list 2..count as re>
           <#assign y = info?replace(first + x, "") />
           <#assign z = y?index_of("/", re) />
           <#assign z = y?substring(0, z) />
           <#assign x = x + z/>
        </#list>
    </#if>  
    <#return (x)>
</#function>

<#-- uri 파라미터 변경하기   -->

