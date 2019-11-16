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

			.alert {
				display: inline-block;
				padding: 15px 25px;
				color: green;
				margin-bottom: 15px;
				border-radius: 3px;
			}

			.alert-success {
				background-color: #d3f2d3;
				border: 1px solid #87cd87;
			}
		</style>
	</head>
	<body>
		Is logged in? ${session.loggedIn?c}

		<ul>
			<#if session.loggedIn>
				<li><a href="/dashboard">Dashboard</a></li>

				<#if session.player.isGuildMaster>
					<li><a href="/guild-management/">Guild management</a></li>
				</#if>
			<#else>
				<li><a href="/">Homepage</a></li>
			</#if>
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