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

ProductID=`date +'%s'`
Date=`date`
DEST=sdl_$ProductID

cd src
mkdir -p $DEST/client
cp -rf client/sdl.jar client/SDLActor.java $DEST/client/.
cp -rf ReleaseNote server setup.sh $DEST/.
sed -i -e "s/_ProductID/$ProductID/" $DEST/ReleaseNote
sed -i -e "s/_Date/$Date/" $DEST/ReleaseNote

mkdir -p ../builds
tar -czf ../builds/$DEST.tgz $DEST
rm -rf $DEST

