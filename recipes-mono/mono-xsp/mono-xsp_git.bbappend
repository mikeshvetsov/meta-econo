# Fix "Error: Transaction check error"
# This seems to be happening because the mono-xsp rpm
# is owning directories, which are also owned by mono-libs
# and mono-gac, which leeds to a conflict.
# This configuration prevents mono-xsp from owning these directories.

DIRFILES=""
