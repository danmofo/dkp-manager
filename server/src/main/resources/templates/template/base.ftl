<#macro general>
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Page title goes here - todo</title>

		<style>
			html {
				font-family: 'Tahoma';
				font-size: 16px;
			}

			label {
				font-weight: bold;
				display: block;
				margin-bottom: 5px;
			}

			input[type="text"] {
				padding: 5px 10px;
			}

			.form-group {
				margin-bottom: 15px;
			}

			.error {
				color: red;
			}
		</style>
	</head>
	<body>
		Is logged in? ${session.loggedIn?c}

		<ul>
			<li><a href="/">Homepage</a></li>
			<li><a href="/guilds">Guilds</a></li>
			<li><a href="/about">About</a></li>
			<li><a href="/contact">Contact</a></li>
			<#if session.loggedIn>
				<form action="/logout" method="POST">
					<input type="submit" value="Log out" />	
				</form>
			<#else>
				<li><a href="/login">Log in</a></li>
				<li><a href="/signup">Sign up</a></li>
			</#if>
		</ul>
		<#nested />	
	</body>
	</html>
</#macro>