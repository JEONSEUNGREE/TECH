<#import "util.ftl" as util/>
<#import "const.ftl" as const/>
<#global use="Used_Skill", backend="Springboot", ssr="Freemarker", db="h2-database", jpa="SpringDataJpa"/>
<#global name="Board">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<#if (1 > 0)>
    <#assign user="Freemarker"/>
    <#assign x="Hello ${user}! + ${name}">
    ${x}
<#else>
    <#assign "x" = 1/>
    #{x}
</#if>
<br><br>
${use} : ${backend} , ${ssr}, ${db}, ${jpa}

<br><br>
<#--  매크로 라이브러리 사용 -->
<@util.makeLink link="${const.springLink}" imgLink="${const.springboot}" 
alt="sprinboot" height="100" width="100" />

<@util.makeLink link="${const.freemarkerLink}" imgLink="${const.freemarker}" 
alt="freemarker" height="100" width="100" />

<@util.makeLink link="${const.h2Link}" imgLink="${const.h2}" 
alt="h2" height="100" width="100" />

<@util.makeLink link="${const.jpaLink}" imgLink="${const.jpa}" 
alt="jpaLink" height="100" width="150" />

<#if (2 > 1)>
    <@util.ajaxMethod url="test" method="POST"/>
<#else>
    i"m Else
</#if>