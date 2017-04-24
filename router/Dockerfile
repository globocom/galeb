FROM maven:3-jdk-8

ENV ROUTER_PORT 8080
ENV ENVIRONMENT_NAME desenv
ENV MANAGER_URL http://127.0.0.1:8000
ENV MANAGER_USER tuxmonteiro
ENV MANAGER_PASS password
ENV ETCD_SERVER http://127.0.0.1:2379
ENV STATSD_HOST 127.0.0.1
ENV STATSD_PORT 8125
ENV SYSLOG_HOST 127.0.0.1
ENV SYSLOG_PORT 514
ENV ENABLE_ACCESSLOG true
ENV ENABLE_STATSD true

EXPOSE $ROUTER_PORT

RUN git clone https://github.com/galeb/galeb-next.git && \
    cd galeb-next/router && \
    mvn clean package -DskipTests && \
    mvn spring-boot:build-info

WORKDIR galeb-next/router

CMD ./start.sh
