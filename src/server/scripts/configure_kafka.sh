########################################################
#       Copyright (C) 2015 GreenFLOPS                  #
#                                                      #
# This file is part of GreenFLOPS projects and can not #
# be copied and/or distributed in any medium or format #
#    without the express permission of GreenFLOPS      #
#                                                      #
#              contact@greenflops.com                  #
########################################################
#!/bin/bash

KAFKA_HOME="/kafka"

mv $KAFKA_HOME/config/server.properties $KAFKA_HOME/config/server.properties.orig
cp /server.properties $KAFKA_HOME/config/server.properties

