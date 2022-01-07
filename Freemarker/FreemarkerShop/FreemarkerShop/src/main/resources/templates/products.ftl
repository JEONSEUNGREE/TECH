<#import "/util/header.ftl" as head />
<#import "/util/footer.ftl" as footer />
<#import "/util/body.ftl" as body />
<#assign titles="(title?json_string)"/>
<#assign m=title?eval/>

<@head.cdn>

</@head.cdn>
<@body.body>
<p>카드 페이지</p>

    <p>${m.[0]}</p>

</@body.body>


<@footer.footer />


