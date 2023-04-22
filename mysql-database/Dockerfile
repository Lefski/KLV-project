FROM mysql:latest

LABEL author="klv"

RUN chown -R mysql:root /var/lib/mysql/

ADD create_klv_database.sql /etc/mysql/create_klv_database.sql

RUN cp /etc/mysql/create_klv_database.sql /docker-entrypoint-initdb.d

EXPOSE 3306