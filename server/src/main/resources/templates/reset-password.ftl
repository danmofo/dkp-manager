<#import "template/base.ftl" as layout />
<#import "/spring.ftl" as spring />

<@layout.general>
    <h1>Reset your password</h1>
	
	<#if token?has_content>
	
		<#if player??>
			<p>Welcome ${player.name}! Let's help you reset your password...</p>
			
			<form action="/reset-password" class="js-reset-password-form">
				
				<div class="form-group">
				    <@spring.bind "resetPasswordForm.password" />
				    <label for=" ${spring.status.expression}">Password</label>
				    <input type="password" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
				    <span class="error error_${spring.status.expression}"></span>
				</div>

				<div class="form-group">
				    <@spring.bind "resetPasswordForm.confirmPassword" />
				    <label for=" ${spring.status.expression}">Confirm password</label>
				    <input type="password" name="${spring.status.expression}" value="${spring.status.value?default('')}" />
				    <span class="error error_${spring.status.expression}"></span>
				</div>

				<span class="error error_passwordsMatch"></span>

				<input type="submit" value="Change password" />

			</form>


		<#else>
			<p>Invalid token.</p>
		</#if>

	<#else>
		<p>No token was specified.</p>
	</#if>

	<script src="/validation-error-handler.js"></script>
	<script src="/reset-password-form.js"></script>

</@layout.general>