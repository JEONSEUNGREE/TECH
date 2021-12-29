<#import "dateUtil.ftl" as dateUtil>
<#import "const.ftl" as const>
<#assign header=["번호", "글제목", "날짜", "내용"]/>
<#--  이미지 링크 매크로   -->
<#macro makeLink link imgLink alt height width>
    <a href="${link}">
       <img src="${imgLink}" alt="${alt}" height="${height}" width="${width}">
    </a>
</#macro>

<#--  엔터 눌러주는 매크로   -->
<#macro pressEnter count>
    <#list 1..count as c>
        <br>
    </#list>
</#macro>

<#--  ajax요청 매크로  -->
<#macro ajaxMethod url method name location>
<script>
    $.ajax({
    method: "${method}",
    url: "http://localhost:7777/${url}",
    data: JSON.stringify({ name: "${name}", location: "${location}" }),
    contentType : 'application/json;charset=utf-8',
    })
    .done(function( res ) {
        alert( res + "Done" );
    })
    .fail(function( err )  {
        alert( err + "Error" );
    });
</script>
</#macro>

<#--  테스트 매크로  -->
<#macro testMethod url method name location>
<script>
    $.ajax({
    method: "${method}",
    url: "http://localhost:7777/${url}",
    data: JSON.stringify({ name: "${name}", location: "${location}" }),
    contentType : 'application/json;charset=utf-8',
    })
    .done(function( res ) {
        alert( res + "Done" );
    })
    .fail(function( err )  {
        alert( err + "Error" );
    });
</script>
</#macro>

<#--  글 작성 매크로   -->
<#macro postBoard read>
<#--  readOnly로 적용  -->
<form method="post" action="/submit">
    <table>
        <tr>
            <td>${header[1]}</td>
            <td><input type="text" style="width: 300px; height: 30px;" name="title" placeholder="input title" ${read}></td>
        </tr>    
        <tr>
            <td>${header[3]}</td>
            <td><textarea style="resize: none; width: 300px; height: 100px;" name="contents" placeholder="contents" ${read}></textarea></td>
        </tr>
    </table>
    <input type="submit" value="제출">
</form>
</#macro>   

<#macro readBoard read board>
<#--  readOnly로 적용  -->
<form method="post" action="/submit">
    <table>
        <tr>
            <td>${header[1]}</td>
            <td><input type="text" style="width: 300px; height: 30px;" name="title" placeholder="${board.title}" ${read}></td>
        </tr>    
        <tr>
            <td>${header[3]}</td>
            <td><textarea style="resize: none; width: 300px; height: 100px;" name="contents" placeholder="${board.contents}" ${read}></textarea></td>
        </tr>
    </table>
    <input type="submit" value="제출">
</form>
</#macro> 

<#--  글 목록 매크로  -->
<#macro board boardLists>
    <table boarder="1">
        <thead>
            <tr>
                <th>
                  ${header[0]}
                </th>
                <th>
                   ${header[1]}
                </th>
                <th>
                   ${header[2]}
                </th> 
            </tr>
        </thead>
        <tbody>
        <#--  model로 받는경우 $사용 X  -->
        <#list boardLists as board>
            <tr>
                <td>
                    ${board.id}
                </td>
                <td>
                    <a href="${const.API_BASEURI}/${const.board}/${board.id}">${board.title}</a>
                </td>
                <td>
                <#--  dateUtil 매크로  -->
                <#--  매크로안에 매크로 호출 받을시 아래와 같이 인자 넘김  -->
                    <@dateUtil.parseDate src="${board.regDate}" /> 
                    <#--  ${board.regDate}  -->
                </td>
            </tr>
        </#list>
        </tbody>    
    </table>
</#macro>
