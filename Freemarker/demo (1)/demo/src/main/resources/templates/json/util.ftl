<#assign first = "https://" />
<#assign head = ["원본", "자른 횟수", "결과", "파라미터", "변경값"] />
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

<#function uriTest info count>
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

<#macro inputData> 
    <p>1. uri "/" 단위로 자르기</p>
   <form method="post" action="/jsonParsing">
        <input type="text" name="uri" placeholder="uri" style="width: 200px;">
        <input type="number" name="number" placeholder="number" style="width: 50px;">
        <input type="submit" value="제출">
   </form>
</#macro>

<#macro parameter> 
    <p>2. 파라미터 변경</p>
   <form method="post" action="/jsonParsing2">
        <input type="text" name="uri" placeholder="uri" style="width: 200px;">
        <input type="text" name="param" placeholder="param" style="width: 50px;">
        <input type="text" name="value" placeholder="value" style="width: 50px;">
        <input type="submit" value="제출">
   </form>
</#macro>

<#assign uri2 = "https://www.inflearn.com/pages=05&sort=05" ,
         param2 = "pages=05",
         param3 = "sort=05" />


<#function changeParam params, value, num>
   <#if params??>
     <#assign y = params?index_of(value) />
     <#assign k = value?length-2 />
     <#assign z = params?substring(y, y+k) + num/>
     <#assign x = params?replace(value, z) />
   </#if>
   <#return (x) >
</#function>


