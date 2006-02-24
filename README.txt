This is the root directory for the GeoAPI project.


- stable   contains the latest GeoAPI release, with minor bug fixes. Changes that do not
           impact the API (javadoc clarifications, bug fixes in our very few implementations
           like code lists, etc.) are applied directly in this directory. Those changes may
           lead to a dot-dot release (e.g. 2.0.1 after 2.0.0).

- pending  contains interfaces under development for possible inclusion in a future GeoAPI
           release. This directory content is much more volatile than the stable directory.
           Changes in this directory need to be submitted to OGC before to be included in a
           dot release (e.g. 2.1.0 after 2.0.0).


The following example show the difference applied in the pending directory for the referencing
packages. Note that you don’t need to local copy (svn checkout) in order to run this command.


  svn diff https://svn.sourceforge.net/svnroot/geoapi/trunk/stable/src/main/java/org/opengis/referencing/
           https://svn.sourceforge.net/svnroot/geoapi/trunk/pending/src/main/java/org/opengis/referencing/
