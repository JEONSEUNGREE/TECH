<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />
<#import "/fragments/util.ftl" as util />

<@header.header />

<@header.bodyHeader>

    <form role="form" action="/order" method="post">

        <div class="form-group">
            <label for="member">주문회원</label>
                <select name="memberId" id="member" class="form-control">
                <option value="">회원선택</option>
                <#if members??>
                    <#list members as member >
                        <option value="${member.id}">
                            ${member.name}
                        </option>
                    </#list>
                </#if>
                </select>
        </div>

        <div class="form-group">
            <label for="item">상품명</label>
                <select name="itemId" id="item" class="form-control">
                <option value="">상품선택</option>
                <#if items??>
                    <#list items as item >
                        <option value="${item.id}">
                            ${item.name}
                        </option>
                    </#list>
                </#if>
                </select>
        </div>

        <div class="form-group">
            <label for="count">주문수량</label>
            <input type="number" name="count" class="form-control" id="count" placeholder="주문 수량을 입력하세요">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <br/>
    <@footer.footer />

</@header.bodyHeader>
