# DKP Manager

A website where users can register their guild and manage DKP in the game World of Warcraft.

- Backend: Java (Spring/Hibernate), MySQL and maybe a little Bash/Groovy.
- Frontend: HTML, CSS and JavaScript. Maybe add some Webpack stuff later on.
- OS: Developing = Windows, Deploying = Linux. Maybe Docker containers in the future.

- Programs used:
	- Sublime Text 3
	- IntellJ Community Edition
	- Git Bash
	- MySQL CLI

## Up next...
- Forgotten password
	- todo: implement the form which collects the new password, reset the token after password has changed. Talk about improvements to this system and flaws that it has.
	- Process
		- When a user created on the website, generate a token and store in the database
		- Send a link with the token as a URL parameter
		- User clicks link
		- Link contains a form with a password field
		- User changes password
		- Generate a new forgotten password token
- Refactor javascript for AJAX-based form - create some common code that's shared between them.

## Features/functions

- Write a forgotten password feature
	- Implement a third-party email service
- Write a reset password feature
- Allow users to change to details
- Write some features for guild masters/people who manage DKP
	- Award DKP to a specific player
		- They need to log the items
	- Decay DKP at fixed intervals
	- Need a way to invite their guild members - invite mechanic, paste a link to someone
- Write some pagination for pages which list items - guild list, dkp history page, players list page 
- Sign the session cookies to prevent tampering
- CSRF protection
- Make it look pretty - add CSS to external file and maybe use SASS or something else.
- Implement Webpack for managing JavaScript
- Different configuration for different environments - production / dev
- Domain name (replace localhost references with new domain) and SSL cert setup
- Session system
	1. Request comes in
		- If cookie exists
			- Load session data
		- If cookie doesn't exist
			- Create new session
	2. Handle request inside controller
		- Do whatever, add/remove session data
	3. When request is finished
	    - Persist cookie to client

## Development

### Windows
- Open MySQL CLI
- Run `source c:/dev/dkp-manager/schema.sql`
- Run app in IntelliJ (for now)