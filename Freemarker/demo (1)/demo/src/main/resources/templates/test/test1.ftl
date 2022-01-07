<#assign seq = ['a', 'b'] />
${seq[0]! '-'}
${seq[1]! '-'}
${seq[2]! '-'}
${seq[3]! '-'}

<#--  <#list seq as s>
    ${s[s]! '-'}
</#list>  -->

<#assign book={"title":"test"}/>
<#assign x="test"/>
${x + ":" + book.title?upper_case}

${x!"testCode"}

<#macro greet person color>
    <p style="color:${color};"> ${person} </p>
    <#nested>
    <#nested>
</#macro>

<@greet person="TestPerson" color="red" >
    TESTPERSON
</@greet>
<br>
<#list ["loop1"] as x >
    ${x}<br>
        <#list ["loop2"] as x >
            ${x}<br>
                <#list ["loop3"] as x >
                    ${x}<br>
                </#list>
            ${x} <br>
        </#list> 
    ${x} <br>
</#list> 

<br>
<#<#assign animals=[{"name":"lion", "size":"large", "price": 1000}]/>
<p>We have these animals:</p>
<table border=1>
  <tr><th>Name<th>Price
  <#list animals as animal>
  <tr>
    <td>
      <#if animal.size == "large"> ${animal.name} </#if>
      <#if animal.size == "large"> ${animal.price} Euros </#if>
    <td>
  </#list>
</table>