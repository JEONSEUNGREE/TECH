<#macro makeLink link imgLink alt height width>
    <a href="${link}">
       <img src="${imgLink}" alt="${alt}" height="${height}" width="${width}">
    </a>
</#macro>

<#macro ajaxMethod url method>
<script>
  $.ajax({
    method: "${method}",
    url: "http://localhost:7777/${url}",
    data: JSON.stringify({ name: "John", location: "Boston" }),
    contentType : 'application/json;charset=utf-8',
    })
    .done(function( res ) {
        alert( res + "Done" );
    })
    .fail(function( err )  {
    alert( err + "Error" );
    });
</script>
</#macro>