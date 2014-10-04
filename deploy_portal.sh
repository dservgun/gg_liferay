#!/bin/sh
TOMCAT_VERSION=tomcat-7.0.42
LIFERAY_BUNDLE=${HOME}/liferay-dev/bundles
TOMCAT_DIR=${LIFERAY_BUNDLE}/${TOMCAT_VERSION}
TOMCAT_LIBS=${TOMCAT_DIR}/webapps/ROOT/WEB-INF/lib

TOMCAT_WEBAPPS=${TOMCAT_DIR}/webapps/ROOT/WEB-INF/classes
CATALINA_LOCALHOST=${TOMCAT_DIR}/conf/Catalina/localhost
PROPERTIES_FILE_NAME=portal-ext.properties
EXT_SPRING=ext-spring.xml
GG_HBM_FILE=portal-hbm-gg.xml
SRC_LIFERAY=../liferay-dev/liferay-portal
SRC_LIFERAY_META_LOC=${SRC_LIFERAY}/portal-impl/src/META-INF
DEST_LIFERAY_META_LOC=${SRC_LIFERAY}/bin/META-INF
GG_CACHE_DEST_CONFIG_DIR=${TOMCAT_WEBAPPS}/META-INF
GG_CACHE_SRC_CONFIG_FILE=liferay-gg-config.xml
GG_CACHE_DEST_CONFIG_FILE=gg-config.xml
GG_SRC_DIR=${HOME}/gridgain-fabric-os-6.5.0
GG_COMPILE_DEST=${HOME}/liferay-portal/lib/development
GG_JAR=gridgain-core-6.5.0.jar

COPY="cp -p"

function validateXML () {
	XMLLINT="xmllint"
	${XMLLINT} $1
	RESULT=$#
	echo "XMLLINT returned ${RESULT}"
	if [ ${RESULT} -ne 0 ]; then
		echo "There is an error. But continuing.."
	fi

}


validateXML ${GG_CACHE_SRC_CONFIG_FILE}

cp ./${PROPERTIES_FILE_NAME} ${TOMCAT_WEBAPPS}
cp ./${EXT_SPRING} ${TOMCAT_WEBAPPS}/META-INF
cp ./ROOT.xml ${CATALINA_LOCALHOST}
cp ./${GG_HBM_FILE} ${DEST_LIFERAY_META_LOC}
cp ./${GG_HBM_FILE} ${TOMCAT_WEBAPPS}/META-INF/
cp ./${GG_CACHE_SRC_CONFIG_FILE} ${GG_CACHE_DEST_CONFIG_DIR}/${GG_CACHE_DEST_CONFIG_FILE}
cp ${GG_SRC_DIR}/libs/${GG_JAR} ${TOMCAT_LIBS}
ls -ltrh ${TOMCAT_WEBAPPS}/${PROPERTIES_FILE_NAME}
ls -ltrh ${TOMCAT_WEBAPPS}/META-INF/${EXT_SPRING}
ls -ltrh ${CATALINA_LOCALHOST}/ROOT.xml
ls -lrth ${DEST_LIFERAY_META_LOC}/${GG_HBM_FILE}
ls -ltrh ${GG_CACHE_DEST_CONFIG_DIR}/${GG_CACHE_DEST_CONFIG_FILE}




