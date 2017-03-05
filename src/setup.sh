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

NC='\033[0m' # No Color
LightGreen='\033[1;32m'
LightBlue='\033[1;34m'

SDL_HOME=$PWD/
SDLSERVER_HOME=$SDL_HOME/server/
SDLCLIENT_HOME=$SDL_HOME/client/
KAFKA_HOME=$SDL_HOME/kafka/

ProductID=`grep ProductID ReleaseNote | awk '{print $3}'`
IMAGENAME=greenflops/sdl-server:$ProductID

cat ReleaseNote

echo "########################################################"
echo "                   Build SDL Server"
echo " 		Image name = $IMAGENAME"
echo "########################################################"
cd $SDLSERVER_HOME
echo "Please enter the SDL Server IP Address to use by inside docker:"
echo "(Expect your input like 192.168.1.14)"
read IPADDR;
echo "SDL Server will use $IPADDR"
sed -i -e "s/your\.host\.name/$IPADDR/g" scripts/server.properties
echo "Build sdl docker image $IMAGENAME"
docker build -t $IMAGENAME .
echo "Run the SDL server $IMAGENAME"
docker run -d --network="host" $IMAGENAME
echo "Done"


echo "########################################################"
echo "               Extract SDL Java Client API"
echo "########################################################"
cd $SDL_HOME
tar -xzf $SDLSERVER_HOME/kafka.tgz
cp $SDLCLIENT_HOME/sdl.jar $KAFKA_HOME/libs/.
cd $SDLCLIENT_HOME
javac -cp "$KAFKA_HOME/libs/*" SDLActor.java


echo -e "${LightGreen}######################################################################${NC}"
echo -e "${LightGreen}  Launch a SDL Actor that gets GUI and Swarm Rows during 1:30 min${NC}"
echo -e "${LightGreen}######################################################################${NC}"
java -cp ".:$KAFKA_HOME/libs/*" SDLActor $IPADDR get 90 &

echo -e "${LightBlue}######################################################################${NC}"
echo -e "${LightBlue}  Launch a SDL Actor that sends GUI and 2 Swarm Tables during 1 min${NC}"
echo -e "${LightBlue}######################################################################${NC}"
java -cp ".:$KAFKA_HOME/libs/*" SDLActor $IPADDR set 60 &

