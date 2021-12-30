<#import "/test/util.ftl" as util />

<#function uri info>
    <#if (info?contains("c"))>
        info?replace("c", "T")
    </#if>
    <#return (info)>
</#function>

${uri("https://cdn.jsdelivr.net/npm")}

<#--  <!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <#assign title="프리마커"?upper_case />
    <h1>${title}</h1>


<div style="display: inline;">
    <div style="display: inline;">
        <input type="text" id="todo" placeholder="write" style="width:200px;">
    </div>
    <div style="display: inline;">
        <input type="checkbox" class="btn-check" id="btn-check-2" checked autocomplete="off">
        <label class="btn btn-primary" for="btn-check-2">click</label>
    </div>
</div>

  </body>
</html>  -->