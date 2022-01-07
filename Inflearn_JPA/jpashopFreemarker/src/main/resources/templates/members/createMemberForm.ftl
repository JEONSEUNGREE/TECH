<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />

<@header.header >
<style>
    .fieldError {
        border-color: #bd2130;
    }
</style>
</@header.header >

<@header.bodyHeader>
     <form role="form" action="/members/new" method="post">
        <spring:bind path = "memberForm">
        <div class="form-group">
            <label for="name">이름</label>
<!--            getter setter 처럼 *가 name에 접근-->
            <#if name??>
            <input type="text" name="name"
                   placeholder="이름을 입력하세요"
                   class="form-control">
            <#else>
            <input type="text" name="name"
                   placeholder="이름을 입력하세요"
                   class="form-control fieldError">
            </#if>
            <#if name??>
            에러
            </#if>

        </div>
        <div class="form-group">
            <label th:for="city">도시</label>
            <input type="text" name="city" class="form-control"
                   placeholder="도시를 입력하세요">
        </div>
        <div class="form-group">
            <label th:for="street">거리</label>
            <input type="text" name="street" class="form-control"
                   placeholder="거리를 입력하세요">
        </div>
        <div class="form-group">
            <label for="zipcode">우편번호</label>
            <input type="text" name="zipcode" class="form-control"
                   placeholder="우편번호를 입력하세요">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
        </spring:bind>
    </form>
    <br/>
    <@footer.footer />

</@header.bodyHeader>

<#--  </div> <!-- /container -->
</body>
</html>
