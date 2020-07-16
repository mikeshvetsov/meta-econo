DESCRIPTION = "webconfig for econo"
LICENSE = "CLOSED"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"




SRC_URI += " \
	file://webcfg.tar.gz \
	file://systemconfig.sh \
"

S="${WORKDIR}"

RDEPENDS_${PN} += "bash"

do_install () {
    install -d ${D}/www
    install -d ${D}/www/pages
    install -d ${D}/opt
   
    cp -r ${S}/webcfg/* ${D}/www/pages

    install -m 0775 ${S}/systemconfig.sh ${D}/opt
}

pkg_postinst_${PN}() {
    chmod -R 0775 $D/www/pages
}


FILES_${PN} += " \ 
    /www \
    /www/pages \
    /www/pages/bin \
    /www/pages/images \
    /opt \
"
