# Project configurations for IDE

This directory contains configuration files for some IDE. Native IDE projects
can be used as an alternative to Maven project for performance or convenience
reasons. However, Maven still the primary way to build GeoAPI and must be run
at least once in order to download the dependencies.

Example of functionalities that can be found in native IDE projects and not
in Maven build:

  * Remove (for temporary tests) the constraint on module dependency order.
  * List of words for the spellchecker.

See the `README.md` files is sub-directories for important IDE-specific
instructions before to commit to SVN any change in the project files.
