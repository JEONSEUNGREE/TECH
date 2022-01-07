<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />
<#import "/fragments/util.ftl" as util />

<@header.header />

<@header.bodyHeader>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>#</th>
                <th>상품명</th>
                <th>가격</th>
                <th>재고수량</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <#if items??>
                <#list items as item >
                        <tr>
                            <td>${util.checkList (item.id)}</td>
                            <td>${util.checkList (item.name)}</td>
                            <td>${util.checkList (item.price)}</td>
                            <td>${util.checkList (item.stockQuantity)}</td>
                            <td>
                            <a href="/items/${item.id}/edit"
                            class="btn btn-primary" role="button">수정</a>
                            </td>
                        </tr>
                </#list>
            </#if>
            </tbody>
        </table>
    <@footer.footer />

</@header.bodyHeader>