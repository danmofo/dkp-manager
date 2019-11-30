<#macro general>
	<!DOCTYPE html>
	<html lang="en">
	<head>
		<meta charset="UTF-8">
		<title>Page title goes here - todo</title>
		<link rel="stylesheet" href="http://localhost:8081/main.css" />
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

		<script src="http://localhost:8081/main.js"></script>
	</body>
	</html>
</#macro>