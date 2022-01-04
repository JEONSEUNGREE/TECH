<#macro mainSet x1>
<script>
function init() {
    const test = "${x1}"
    let test = p
	localStorage.setItem(test, "hidden");
    if(p!) {
        localStorage.setItem(p, "hidden");
    }
    
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
<#--  p, l, b,k,q,k  -->
<#macro positionSet>
    <@mainSet />
        <#list 0..8 as p>
            <script>
               init(p)
            </script>
        </#list>
</#macro>
