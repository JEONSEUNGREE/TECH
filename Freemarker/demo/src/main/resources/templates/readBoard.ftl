<div class="readBoard">
    <form>
        <table border="1" style="width=500">
           <thead>
                     <th>제목</th>
                     <th>내용</th>
                     <th>날짜</th>
        </thead>
        <tbody>
                <td><input placeholder="${board.title}" readonly></td>
                <td><textarea name="contents" value="contents" placeholder="contents" readonly>${board.regDate}</textarea></td>
                <td>${board.contents}</td>
        </tbody>
        </table>
    </form>
</div>