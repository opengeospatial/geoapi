# GeoAPI Python README

## Static Type Checking
Static type checking for GeoAPI's Pytnon implementation is accomplished using [mypy](https://mypy.readthedocs.io/en/stable/index.html).


## Development Environment
### Setup for tests, development and build
We strongly recommend the use of a virtual environment for development, testing, and building.

To do this, simply execute the following commands in the project's directory:

```bash
# change the working directory to the GeoAPI's Python source directory
cd ./geoapi/src/main/python

# use venv to create a Python virtual environment in a directory called `.venv`
python -m venv .venv

```

And then, everytime you want to use the project, activate the virtual environment:

```bash
# on Linux systems
source .venv/bin/activate
```

### Execute the tests
You first need to make sure that each dependency is installed.

```bash
pip install -e ".[test, dev]"
```

Once that is finished, execute the tests by running the following command:

```bash
pytest ./tests
```

### Build the library wheel file
Be sure to [change to GeoAPI's Python source directory and activate the virtual environment](#setup-for-tests-development-and-build) first.

After that, install the required tools to build the library.

```bash
pip install -e ".[build, dev]"
```

Then, run the build command

```bash
python -m build --outdir ../../../../target/python
```

The result will be located in the `dist` directory.

### Install the library
To install the file from a wheel file ([Build the library wheel file](#build-the-library-wheel-file)).
Just install the wheel file with `pip`.

```bash
# replace the <...> with the version you need
pip install ../../../../target/python/geoapi-<version>-py3-<abi>-<platform>.whl
```

### Generate the library documentation
The documentation for the Python portion of GeoAPI is created using [MkDocs](https://www.mkdocs.org/), [mkdocstrings](https://mkdocstrings.github.io/) for auto-generating the documentation from docstrings, and [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/) for styling the pages.

Be sure to [change to GeoAPI's Python source directory and activate the virtual environment](#setup-for-tests-development-and-build) first.

Then, install the required dependencies to build the documentation site.

```bash
pip install -e ".[dev, doc]"
```

To preview the documentation site:
```bash
mkdocs serve
```

To build the documentation site:
Building
```bash
mkdocs build --clean --strict --verbose --site-dir ../../../../target/docs-python
```
The result is located in the `geoapi/target/docs-python` directory, where "geoapi" is the root of the entire project/repository.

#### Material for MkDocs Reference Links

[Creating a Material for MkDocs site](https://squidfunk.github.io/mkdocs-material/creating-your-site/)

[Publishing a Material for MkDocs site](https://squidfunk.github.io/mkdocs-material/publishing-your-site/)
