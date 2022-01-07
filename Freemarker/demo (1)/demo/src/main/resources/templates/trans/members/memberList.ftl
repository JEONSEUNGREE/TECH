<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />

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
                    <td>${checkList (member.id)}</td>
                    <td>${checkList (member.name)}</td>
                    <td>${checkList (member.address.city)}</td>
                    <td>${checkList (member.address.street)}</td>
                    <td>${checkList (member.address.zipcode)}</td>
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