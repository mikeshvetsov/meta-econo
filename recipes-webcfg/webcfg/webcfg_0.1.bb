DESCRIPTION = "webconfig for econo"
LICENSE = "CLOSED"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"




SRC_URI += " file://webcfg.tar.gz"

S="${WORKDIR}/webcfg"

do_install () {
    install -d ${D}/www
    install -d ${D}/www/pages
   
   # install -m 775 -d ${D}${bindir}/codesys
   # install -m 775 -d ${D}${bindir}/codesys/CODESYSControl
   
    cp -r ${S}/* ${D}/www/pages

}



FILES_${PN} += " \ 
    /www \
    /www/pages \
"
