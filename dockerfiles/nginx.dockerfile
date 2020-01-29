# Dockerfile for nginx
FROM nginx

# todo: this should only happen locally, in production the images will be stored somewhere else.
RUN mkdir -p /app/images

# Clear out default configuration
RUN rm /etc/nginx/conf.d/default.conf

# Add our configuration
COPY conf/dkpmanager.conf /etc/nginx/conf.d/dkpmanager.conf

# Add certs - todo: maybe have a way to change the folder which certs come from? When we deploy this we'll most likely have a different domain and not moff.rocks.
RUN mkdir -p /app/certs
COPY conf/certs/moff.rocks/fullchain.pem /app/certs/fullchain.pem
COPY conf/certs/moff.rocks/privkey.pem /app/certs/privkey.pem