<#import "template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Forgotten password</h1>
    <p>Just enter your email address and we'll send you an email with details on how to reset your password.</p>

    <#if message??>
        <div class="alert alert-success">
            ${message}
        </div>
    </#if>

    <form action="/forgotten-password" method="POST" class="js-forgotten-password-form">

        <div class="form-group">
            <@spring.bind "forgottenPasswordForm.email" />
            <label for=" ${spring.status.expression}">Email address</label>
            <input type="email" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
            <span class="error error_${spring.status.expression}"></span>
        </div>

        <input type="submit" value="Send forgotten password email" />
    </form>

    <script src="/validation-error-handler.js"></script>
    <script src="/forgotten-password-form.js"></script>
</@layout.general>