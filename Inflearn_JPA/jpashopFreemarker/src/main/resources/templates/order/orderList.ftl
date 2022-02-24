<#import "/fragments/header.ftl" as header />
<#import "/fragments/footer.ftl" as footer />
<#import "/fragments/util.ftl" as util />


<#assign orderStatus=orderStatus/>

<@header.header />

<@header.bodyHeader>
        <div>
            <form class="form-inline">
                <spring:bind path = "orderSearch">
                <div class="form-group mb-2">
                    <input type="text" name="memberName" class="formcontrol" placeholder="회원명"/>
                </div>
                <div class="form-group mx-sm-1 mb-2">
                    <select name="orderStatus" class="form-control">
                        <option value="">주문상태</option>
                            <#list orderStatus as status>
                                <option value="${status}">${status}
                                </option>
                        </#list>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary mb-2">검색</button>
            </form>
        </div>
        <table class="table table-striped"> 
            <thead>
            <tr>
                <th>#</th>
                <th>회원명</th>
                <th>대표상품 이름</th>
                <th>대표상품 주문가격</th>
                <th>대표상품 주문수량</th>
                <th>상태</th>
                <th>일시</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            
            <#if orders??>
                <#list orders as item >
                    <tr>
                        <td>${util.checkList (item.id)}</td>
                        <td>${util.checkList (item.member.name)}</td>
                        <td>${util.checkList (item.orderItems[0].item.name)}</td>
                        <td>${util.checkList (item.orderItems[0].orderPrice)}</td>
                        <td>${util.checkList (item.orderItems[0].count)}</td>
                        <td>${util.checkList (item.status)}</td>
                        <td>${util.checkList (item.orderDate)}</td>
                        <td>
                            <#if item.status.name() == 'ORDER'>
                                <a href="#" onclick="cancel(${item.id})" class="btn btn-danger">CANCLE</a>
                            </#if>
                        </td>
                    </tr>
                </#list>
            </#if>
            </tbody>    
        </table>
    <@footer.footer />

</@header.bodyHeader>
    <script>
        function cancel(id) {
            var form = document.createElement("form");
            form.setAttribute("method", "post");
            form.setAttribute("action", "/orders/" + id + "/cancel");
            document.body.appendChild(form);
            form.submit();
        }
    </script>
