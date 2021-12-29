<#assign var=navParents/>

<#list categories as category>
    <div class="nav" style="color: #1C3A5B">
        <a href="/category/${category.id}">${category}</a>
    </div>
</#list>

<#macro