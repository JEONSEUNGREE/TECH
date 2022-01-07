<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />

<@header.header />

<@header.bodyHeader>
    <form method="post">
        <spring:bind path = "form">
        <input type="hidden" name="id" value="${form.id}"/>
        <div class="form-group">
            <label for="name">상품명</label>
            <input type="text" name="name" class="form-control" value="${form.name}"
                   placeholder="이름을 입력하세요" />
        </div>
        <div class="form-group">
            <label for="price">가격</label>
            <input type="number" name="price" class="form-control" value="${form.price}"
                   placeholder="가격을 입력하세요" />
        </div>
        <div class="form-group">
            <label for="stockQuantity">수량</label>
            <input type="number" name="stockQuantity" class="form-control" value="${form.stockQuantity}"
            placeholder="수량을 입력하세요" />
        </div>
        <div class="form-group">
            <label for="author">저자</label>
            <input type="text" name="author" class="form-control" value="${form.author}"
                   placeholder="저자를 입력하세요" />
        </div>
        <div class="form-group">
            <label th:for="isbn">ISBN</label>
            <input type="text" name="isbn" class="form-control" value="${form.isbn}"
                   placeholder="ISBN을 입력하세요" />
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <@footer.footer />

</@header.bodyHeader>