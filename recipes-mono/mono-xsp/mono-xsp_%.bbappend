FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"


SRC_URI += " file://fastcgi-mono-server"

do_install_append () {
	install -d ${D}${bindir}
    install -m 0755 ${WORKDIR}/fastcgi-mono-server ${D}${bindir}/fastcgi-mono-server
}
