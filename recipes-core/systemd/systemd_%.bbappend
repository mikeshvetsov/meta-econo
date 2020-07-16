FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://eth0.network \
    file://eth1.network \
"

PACKAGECONFIG_append = " --disable-polkit"

do_install_append() {
    install -m 0775 -d ${D}${sysconfdir}/systemd/network
    install -m 0775 ${WORKDIR}/eth0.network ${D}${sysconfdir}/systemd/network
    install -m 0775 ${WORKDIR}/eth1.network ${D}${sysconfdir}/systemd/network
}

pkg_postinst_${PN}() {
	chmod 0775 $D${sysconfdir}/systemd/network/eth0.network
	chmod 0775 $D${sysconfdir}/systemd/network/eth1.network
	chmod 0775 $D${sysconfdir}/systemd/network
}