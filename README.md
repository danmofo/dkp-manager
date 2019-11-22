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
- Allow users to change to details
- Add a 404 page for when pages can't be found. Show the template that couldn't be found.
- Add a 5xx page for when pages throw an error, and in development, print the stack trace to the webpage.
- Write some pagination for pages which list items - guild list, dkp history page, players list page 
- Implement Webpack for managing JavaScript
- Make it look pretty - add CSS to external file and maybe use SASS or something else.
	- Look at Grid/Flexbox maybe?!? Havne't looked at those much yet.

## Features/functions

- Fixing generated SQL by modifying object mappings.
- CSRF protection
- Expire forgotten password token after a set amount of time.
- Different configuration for different environments - production / dev
- Domain name (replace localhost references with new domain) and SSL cert setup
- Add the ability to edit a DKP decay interval.
- Add the ability to decay DKP at intervals using percentages rather than fixed numbers.
- Automated tests?!?!
- Manually edit a players DKP
- need a way to specify guild ownership when signing up...how do other websites do this? Maybe some blizzard API that can read guild metadata or players in a guild?

## Development

### Windows
- Open MySQL CLI
- Run `source c:/dev/dkp-manager/schema.sql`
- Run app in IntelliJ (for now)
