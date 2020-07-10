DESCRIPTON = "F&S Startup scripts"
LICENSE = "MIT"

#default license in /mnt/yocto/fsl-release-bsp-l4.1.15_2.0.0-ga/sources/poky/LICENSE
#LIC_FILES_CHKSUM = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

SRC_URI = "file://fsdistro-x11.sh file://fsalias.sh file://systemd-fsgetty-generator "

HAS_X11 = "${@bb.utils.contains("DISTRO_FEATURES", "x11", "yes", "no", d)}"

inherit systemd

FILES_${PN} = " ${systemd_unitdir}/system-generators/systemd-fsgetty-generator \
				${sysconfdir}/profile.d/fsalias.sh \
				${sysconfdir}/profile.d/fsdistro-x11.sh \
"

do_install() {
    install -d ${D}${sysconfdir}/profile.d/
	install -d ${D}${systemd_unitdir}/system-generators/


    if [ "${HAS_X11}" = "yes" ]; then
		install -m 0755 ${WORKDIR}/fsdistro-x11.sh  ${D}${sysconfdir}/profile.d/
    fi

	install -m 0755 ${WORKDIR}/fsalias.sh  ${D}${sysconfdir}/profile.d/
	install -m 0755 ${WORKDIR}/systemd-fsgetty-generator ${D}${systemd_unitdir}/system-generators/

}
