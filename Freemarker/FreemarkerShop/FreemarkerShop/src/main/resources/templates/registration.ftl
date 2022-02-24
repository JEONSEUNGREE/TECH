<#import "/util/macro.ftl" as ma />
<p>회원가입</p>

<form action="/registration" method="post">
    <@ma.membership type="text" name="name" placeholder="name" />
    <@ma.membership type="text" name="email" placeholder="email" />
    <@ma.membership type="password" name="password" placeholder="password" />
    <input type="submit" value="제출">
</form>