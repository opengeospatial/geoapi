         ----------------------------------------------------------
           GeoAPI bundles and API changes documentation generator
         ----------------------------------------------------------


This directory provides two Unix shell scripts, together with the dependencies
required for running them:

  pack.sh - creates a convenience ZIP file providing some commonly used files
  in a single location. To generate the bundle file, just invoke the "pack.sh"
  script from any directory after a successful Maven build.

  jdiff.sh - generates HTML pages documenting the API changes between
  the latest GeoAPI stable release and the current development (trunk).
  To generate those pages, just invoke the "jdiff.sh" script from any
  directory after a successful Maven build.

For more information, see http://www.geoapi.org/tools.


CREDIT: The "jdiff.jar" file provided in this directory is derived from the
http://jdiff.sourceforge.net/ project, with slight modifications documented
at http://www.geoapi.org/tools.
