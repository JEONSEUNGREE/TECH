
<#import "/freemarker/util.ftl" as util>
<#import "/freemarker/dateUtil.ftl" as dateUtil>

<#--  매크로로 받을때 인자값 ""빼도록 하기  -->
<@util.board boardLists=boardList />
<@util.pressEnter count=2/>