<#import "/freemarker/util.ftl" as util />
<#--  템플릿못찾는 경우 에러발생  -->
<#assign testTemplate="testTemplate" />
<#--  템플릿못찾는 경우 예외처리하여 empty로 출력한다. 단 에러 확인 불가하다. -->
<#assign tags = (.get_optional_template('tags.ftl').import())!{}>

<#assign tag = .get_optional_template('tag.ftl')/>

<#if tag.exists>
    tag was found:
<#else>
    tag is missing
</#if>


<@util.pressEnter count=5 />

${testTemplate}

${mouse!"no_mouse"}


<#--  아래 문법은 다음과같다.  -->
<#--  some.ftl이 없는경우 some-fallback.ftl을 호출 하고 그것도 없는경우 ultimatefallback 매크로를 실행한다.  -->
<#macro ultimateFallback>
  Something
</#macro>

<@(
    <#--  만일 재귀호출시 스택터짐  -->
  .get_optional_template('team.ftl').include!
  .get_optional_template('some-fallback.ftl').include!
  ultimateFallback
) />

