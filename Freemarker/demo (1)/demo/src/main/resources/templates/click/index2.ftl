<#include "head.ftl"/>
<#import "util.ftl" as util/>
<#import "/freemarker/util.ftl" as imgUtil/>
<#import "/freemarker/const.ftl" as const/>

<input type="submit" name="click" onclick="add()" value="클릭">

<@imgUtil.makeLink link="${const.springLink}" imgLink="${const.springboot}" 
alt="sprinboot" height="100" width="100" />