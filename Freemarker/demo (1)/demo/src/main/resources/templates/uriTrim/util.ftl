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

<#--  <#assign inflearn="https://www.inflearn.com/pages/6th-birthday-20211220/?utm_source=google_brand_search&utm_medium=cpc&utm_campaign=inflearn_%ED%8A/%B8%EB%9E%98%ED%94%BD_%EC%9D%B4%EB%B2%A4%ED%8A%B8_6th-birthday-20211220&utm_content=%EC%9E%A0%EC%9E%AC%EA%B3%A0%EA%B0%9D_%EC%A0%84%EC%B2%B4&utm_term=211223_will&gclid=EAIaIQobChMI1JWpoeaM9QIVQllgCh3vqADyEAAYASAAEgJqw_D_BwE" />

<#assign testUri = "https://www.inflearn.com/pages=05&sort=05" />


<#assign first = "https://" />

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

<#function parameterChange info parameter>
   <#if (info?index_of(parameter + "=") != 0)>
       <#assign x = info?index_of(parameter + "=") />
       <#assign y = info?keep_before(x) />
    <#else>
       <#assign x = 0 />
   </#if>
    <#return (y)>
</#function>

${mainPageCutter (inflearn,2)}

${parameterChange (testUri, "sort") }  -->
