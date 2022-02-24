<#import "util.ftl" as util >

    
<p> ${util.head[0]} : ${uri}</p>
<p> ${util.head[3]} : ${param}</p>
<p> ${util.head[4]} : ${value}</p>

<p> ${util.head[2]} : ${util.changeParam(uri, param, value)} </p>
