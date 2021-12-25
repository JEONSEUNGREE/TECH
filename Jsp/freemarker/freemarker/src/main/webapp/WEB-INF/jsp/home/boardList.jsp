<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="board.*, java.util.List, service.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">
        <body>
            <h1>게시판 목록입니다.</h1>
            <table style="width: 1000px; text-align: center; " border="1">
                <th>
                    <h3>보드번호</h3>
                </th>
                <th>
                    <h3>제목</h3>
                </th>
                <th>
                    <h3>날짜</h3>
                </th>
                <c:forEach var="board" items="${boardList}">
                    <tr>
                        <td>
                            <p>${board.getId()}</p>
                        </td>
                        <td>
                            <a href="boardReadPage/${board.getId()}">
                                <p>${board.getTitle()}</p>
                            </a>
                        </td>
                        <td>
                            <p>${board.getRegDate()}</p>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </body>
        </html>