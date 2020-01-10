# Dockerfile for nginx
FROM nginx

RUN rm /etc/nginx/conf.d/default.conf
COPY conf/dkpmanager.conf /etc/nginx/conf.d/dkpmanager.conf