# DKP Manager

A website where users can register their guild and manage DKP in the game World of Warcraft.

**This is just a fun side-project because I couldn't think of a better idea. If I was seriously
considering this idea, I would just write a WoW plugin.**

- Backend: Java (Spring/Hibernate), MySQL and a little Bash/Groovy.
- Frontend: HTML, CSS and JavaScript, making use of Webpack.
- OS: Developing = Docker containers, Deploying = Probably Docker containers on Linux.

- Programs used:
	- Sublime Text 3
	- IntellJ Community Edition
	- Git Bash
	- MySQL CLI

## Up next...
- Write some pagination for pages which list items - guild list, dkp history page, players list page 
- Allow users to change to details
- Add a 404 page for when pages can't be found. Show the template that couldn't be found.
- Add a 5xx page for when pages throw an error, and in development, print the stack trace to the webpage.
- Make it look pretty - maybe use SASS or something else.
	- Look at Grid/Flexbox maybe?!? Havne't looked at those much yet.
- At some point we need to look at implementing a HTTP server which proxies static asset requests to a 3rd party CDN and only
certain requests actually make it to our backend, at the minute, all request are going to the backend (even for things like the
favicon, which is causing some errors to be thrown when it doesn't exist.).

## Webpack notes
- We need a prod config.

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

Development should be the same on all platforms now this project is using Docker.

1. When first checking out/working on the project, run `./run-dev`. This will start the database, rebuild the Docker images and 
watch frontend assets for changes. You should do this any time you start working on the project.
2. After editing Java:
	- If a dependency has changed, run `./rebuild`
	- If only Java has changed, run `./rebuild --fast`, this will be significantly quicker than `./rebuild`.
3. When editing client code:
	- If it was a Freemarker template, just reload the page.
	- IF it was JS/CSS, it will be loaded automatically.
4. When done, run `docker-compose down` from the root directory, this will shut down all services (db/app).