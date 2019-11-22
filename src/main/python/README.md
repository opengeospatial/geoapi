# GeoAPI for python

This Python module offers implementation-neutral interfaces for OGC/ISO standards.
Those interfaces can be implemented by GDAL wrappers or by other implementations.


## Compilation from the sources

The following can be executed on the command-line from the GeoAPI project root.
This command will create a `target/dist/opengis-4.0a0.tar.gz` file:

```
python3 src/main/python/build.py sdist
```

If any version of this package has been installed before,
it should be uninstalled first by the `pip uninstall` command (otherwise the uninstall can be skipped).
Uninstallation and installation commands need to be run as administrator, which is done by `sudo`:

```
sudo pip uninstall opengis
sudo pip install dist/opengis-4.0.tar.gz
```
