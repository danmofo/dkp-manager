<#import "template/base.ftlh" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Log in</h1>

    <form action="/login" method="POST" class="js-login-form">

        <div class="form-group">
            <@spring.bind "loginForm.email" />
            <label for=" ${spring.status.expression}">Email address</label>
            <input type="email" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <div class="form-group">
            <@spring.bind "loginForm.password" />
            <label for=" ${spring.status.expression}">Password</label>
            <input type="password" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <input type="submit" value="Log in" />

    </form>

    <p>If you don't have an account, you can <a href="/signup">Sign up here.</a></p>
    <p>Forgotten your password? <a href="/forgotten-password">Reset your password</a></p>
</@layout.general>