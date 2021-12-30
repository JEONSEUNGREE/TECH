<#macro mainSet x1>
<script>
function init() {
    const test = "${x1}"
	localStorage.setItem(test, "hidden");
}
</script>
</#macro>

<#macro pressKey>
    <button onclick="init()">스크립트</button>
</#macro>

<#macro form>
    <table border="1">
        <#list 0..7 as rowcell>
            <tr style="width:50px; height:50px;">
                <#list 0..7 as colcell>
                    <th style="width:50px; height:50px;"></th>
                </#list>
            </tr>
        </#list> 
    </table>
</#macro>
<#--  p,l,b,k,q,k  -->
<#macro positionSet>
    <@mainSet />
        <#list 0..8 as p>
            init(p)
        </#list>
    <#--  초기 로컬저장소  -->
    <#--  좌표 x , y로 구분  -->
    <#--  한번에 한개만 움직임  -->
</#macro>

<#--  0.0 ~ 8.8  -->