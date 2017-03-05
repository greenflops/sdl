########################################################
#       Copyright (C) 2015 GreenFLOPS                  #
#                                                      #
# This file is part of GreenFLOPS projects and can not #
# be copied and/or distributed in any medium or format #
#    without the express permission of GreenFLOPS      #
#                                                      #
#              contact@greenflops.com                  #
########################################################

# Kafka and Zookeeper
FROM java:openjdk-8-jdk

# Install Kafka
RUN apt-get update && \
    apt-get install -y supervisor net-tools vim

COPY kafka.tgz /kafka.tgz

RUN cd / && \
    tar -xzf /kafka.tgz && \
    mkdir -p /var/log/supervisor

COPY scripts/server.properties /server.properties
COPY scripts/supervisord.conf /etc/supervisor/conf.d/supervisord.conf
COPY scripts/configure_kafka.sh /usr/bin/configure_kafka.sh

RUN /usr/bin/configure_kafka.sh

# 2181 is zookeeper, 9092 is kafka
EXPOSE 2181 9092

CMD ["/usr/bin/supervisord", "-n"]

