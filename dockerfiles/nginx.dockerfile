# Dockerfile for nginx
FROM nginx

# todo: this should only happen locally, in production the images will be stored somewhere else.
RUN mkdir -p /app/images

RUN rm /etc/nginx/conf.d/default.conf
COPY conf/dkpmanager.conf /etc/nginx/conf.d/dkpmanager.conf
