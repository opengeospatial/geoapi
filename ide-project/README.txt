This directory contains configuration files for some IDE. They are used as a
complement, not a replacement, for the Maven build.  Maven still the primary
way to build GeoAPI  and  must be run at least once in order to download the
dependencies. There is usually no additional configuration needed since some
IDE like NetBeans can open a Maven project directly. However for extensive
work on GeoAPI, a native NetBeans (for example) project is sometime faster
and more convenient than Maven projects. For example the native project may
build GeoAPI with more debugging options enabled, and may also contains IDE-
specific information not provided by Maven, like list of words for the
spellchecker.
