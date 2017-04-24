FROM maven:3-jdk-8

ENV HEALTH_PORT 7000
ENV ENVIRONMENT_NAME desenv
ENV MANAGER_URL http://127.0.0.1:8000
ENV MANAGER_USER tuxmonteiro
ENV MANAGER_PASS password

EXPOSE $HEALTH_PORT 7800-7900:7800-7900 9000-9100:9000-9100

RUN git clone https://github.com/galeb/galeb-next.git && \
    cd galeb-next/health && \
    mvn clean package -DskipTests && \
    mvn spring-boot:build-info

WORKDIR galeb-next/health

CMD ./start.sh
