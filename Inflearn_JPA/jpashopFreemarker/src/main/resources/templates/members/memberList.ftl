<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />
<#import "/fragments/util.ftl" as util />

<@header.header />

<@header.bodyHeader>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>이름</th>
                <th>도시</th>
                <th>주소</th>
                <th>우편번호</th>
            </tr>
            </thead>
            <tbody>
            <#if members??>
                <#list members as member >
                    <tr>
                        <td>${util.checkList (member.id)}</td>
                        <td>${util.checkList (member.name)}</td>
                        <td>${util.checkList (member.address.city)}</td>
                        <td>${util.checkList (member.address.street)}</td>
                        <td>${util.checkList (member.address.zipcode)}</td>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    <@footer.footer />

</@header.bodyHeader>

<#--  method  -->
<#function checkList param>
    <#if param??>
        ${param}
    <#else>
        <#assign param="">
    </#if>
    <#return (param)>
</#function>