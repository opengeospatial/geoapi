Files in this directory are shared by two modules:

* geoapi/test
* geoapi-conformance/main

Conceptually those files are part of `geoapi-conformance`.
But they are needed for testing the `geoapi` module, which
cannot be done without introducing a circular dependency.
This `shared` directory make easier to provide a copy of
those files to `geoapi/test`
