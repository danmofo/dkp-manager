#!/usr/bin/env bash

# Generates the SSL certicate for moff.rocks and places them in the conf/certs/moff.rocks folder.

repo_dir=$(pwd)

docker build -f dockerfiles/letsencrypt.dockerfile -t dmoffat/dkpmanager_letsencrypt .
docker run -v "$repo_dir/conf/certs:/certs" -it dmoffat/dkpmanager_letsencrypt