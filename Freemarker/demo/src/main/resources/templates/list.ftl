<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Freemarker Guide</title>
</head>

<body>
    <h1>Welcome!!</h1>

    <div class="header">
        ${header}
    </div>
    <div class="main">
        <table border="1" style="width=500">
           <thead>
                <tr>
                   <th>번호</th>
                     <th>제목</th>
                   <th>날짜</th>
              </tr>
        </thead>
        <tbody>
                <#list boardList as board>
                <td><p>${board.id}</p></td>
                <#--  a태그는 최하단 노드에 두기  -->
                   <td><a href="read/${board.id}">${board.title}</a></td>
                <td>${board.regDate}</td>
                </#list>
        </tbody>
    </table>

        
    </div>
     <div class="footer">
        ${footer}
    </div>
</body>

</html>