<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="board.*, java.util.List" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html lang="ko">
        <body>
            <table style="width: 800px; height: 500px;">
                <tr style="height: 100px;">
                    <th colspan="2" style="background-color: aqua; text-align: center;"> 게시판 글쓰기 양식</th>
                </tr>
                <tr>
                    <td><input type="text" class="form-control" placeholder="${board.getTitle()}" name="title"
                            maxlength="50" style="height: 80px; width: 800px; font-size: x-large;" readonly></td>
                </tr>
                <tr>
                    <td><textarea class="form-control" placeholder="${board.getContent()}" name="content"
                            maxlength="255" style="height: 300px; width: 800px;" readonly></textarea></td>
                </tr>
            </table>
            <button type="submit" value="modify" style="width: 50px; height: 25px;">수정</button>
            <button type="submit" value="delete" onclick="location.href='delete/${board.getId()}'"
                style="width: 50px; height: 25px;">삭제</button>
        </body>
        </html>