#!/usr/bin/env bash

docker build -f dockerfiles/letsencrypt.dockerfile -t dmoffat/dkpmanager_letsencrypt . && docker run -it dmoffat/dkpmanager_letsencrypt