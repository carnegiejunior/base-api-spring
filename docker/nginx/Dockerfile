FROM nginx:latest
LABEL maintainer="Sebastião Carnegie" \
    description="Nginx for api"
COPY /public /var/www/public
COPY /docker/config/nginx.conf /etc/nginx/nginx.conf
RUN chmod 755 -R /var/www/public
EXPOSE 80 443
ENTRYPOINT ["nginx"]
# Parametros extras para o entrypoint
CMD ["-g", "daemon off;"]



RUN rm -rf /etc/nginx/conf.d/default.conf
COPY ./default.conf /etc/nginx/conf.d/
COPY ./build/ /usr/share/nginx/html
RUN touch /var/run/nginx.pid && \
    chown -R 1001:1001 /var/run/nginx.pid && \
    chown -R 1001:1001 /var/cache/nginx && \
    chmod -R 777 /var/log/nginx /var/cache/nginx/ && \
    chmod -R 777 /etc/nginx/* && \