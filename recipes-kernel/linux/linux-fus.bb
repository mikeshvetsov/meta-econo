# Copyright (C) 2016 F&S Elektronik Systeme GmbH
# Released under the GPLv2 license

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d7810fab7487fb0aad327b76f1be7cd7"

inherit kernel

SUMMARY = "Linux Kernel for F&S i.MX6-based boards and modules"

require recipes-kernel/linux/linux-dtb.inc

DEPENDS += "lzop-native bc-native"

COMPATIBLE_MACHINE = "(mx6)"

SRC_URI = "git://github.com/beserviceos/linux-fs.git;protocol=git"
SRCREV = "27e94fa8f64c176f87a9531dabaca276165a22c9"

S = "${WORKDIR}/git"

SRC_URI += "file://patch-4.1.15-rt18.patch \
            file://0001-fix-build.patch \
            file://0002-no-split-ptlocks.patch \
            file://0003-Work-around-CPU-stalls-in-the-imx-sdma-driver.patch \
            file://defconfig \
           "


PV = "1.0"

# We need to pass it as param since kernel might support more then one
# machine, with different entry points
KERNEL_EXTRA_ARGS += "LOADADDR=${UBOOT_ENTRYPOINT}"

FSCONFIG_mx6 = "fsimx6_defconfig"
FSCONFIG_mx6sx = "fsimx6sx_defconfig"
FSCONFIG_mx6ul = "fsimx6ul_defconfig"
FSCONFIG_mx7ulp = "fsimx7ulp_defconfig"


MACHINE_USES_VIVANTE_KERNEL_DRIVER_MODULE = "0"


#do_extraunpack () {
#	mv ${WORKDIR}/linux-fus/* ${S}/
#}


#kernel_do_configure_prepend() {
#	install -m 0644 ${S}/arch/${ARCH}/configs/${FSCONFIG} ${WORKDIR}/defconfig
#}

# Prevent the galcore-module from beeing build, because it is already
# included in the F&S-Linux-Kernel as a build-in
RPROVIDES_kernel-image += "kernel-module-imx-gpu-viv"