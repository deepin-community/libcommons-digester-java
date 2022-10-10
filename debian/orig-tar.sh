#!/bin/sh -e

# called by uscan with '--upstream-version' <version> <file>
UDIR=commons-digester-$2-src
DDIR=libcommons-digester-java-$2.orig

# Remove ^M CRLF line terminators
tar -z -x -f $3
mv $UDIR $DDIR
(cd $DDIR; find -type f|xargs perl -pi -e 's/\r$//g')
GZIP=--best tar -c -z -f $3 $DDIR
rm -rf $DDIR $UDIR

# move to directory 'tarballs'
if [ -r .svn/deb-layout ]; then
    . .svn/deb-layout
    mv $3 $origDir
    echo "moved $3 to $origDir"
fi

exit 0
