<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
    import="board.*, java.util.List, service.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">
        <body>
            <c:choose>
                <c:when test="${1 > 0}">
                    1은 0보다 큽니다.
                </c:when>
                <c:when test="${2 > 0}">
                    2도 0보다 큽니다.
                </c:when>
            </c:choose>
            <br>
            <a href="boardList">게시판</a>
            <a href="post">등록</a>
        </body>
        </html>