#!/usr/bin/env bash

# Generates the SSL certicate for moff.rocks and places them in the conf/certs/moff.rocks folder.

# todo: Move the commands which generate the certificate and move it to the correct directory into the dockerfile and make it so that we
# can just run the container and it will generate the certificates and put them in a folder which is shared with the host machine.

repo_dir=$(pwd)

docker build -f dockerfiles/letsencrypt.dockerfile -t dmoffat/dkpmanager_letsencrypt .
docker run -v "$repo_dir/conf/certs:/certs" -it dmoffat/dkpmanager_letsencrypt

# cp /etc/letsencrypt/live/dev.moff.rocks/* /certs