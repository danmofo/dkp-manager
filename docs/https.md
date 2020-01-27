# Notes about HTTPS

## What is HTTPS
HTTPS is just the HTTP protocol with an encryption layer on top of it. It serves 2 main purposes:
- Verifies you are talking directly to the server you think you are talking to.
- Ensures only the server can read what you sent it, and only you can read what it sends back.

Anyone can intercept every single one of the messages you exchange with a server and still not be able to read anything that is sent.

See https://robertheaton.com/2014/03/27/how-does-https-actually-work/ for a full explanation.