# GeoAPI Python REAME

## Static Type Checking
Static type checking for GeoAPI's Pytnon implementation is accomplished using
[mypy](https://mypy.readthedocs.io/en/stable/index.html).


## Creating Documentation for GeoAPI Python
The documentation for the Python portion of GeoAPI is created using [MkDocs](https://www.mkdocs.org/),
[mkdocstrings](https://mkdocstrings.github.io/) for auto-generating the documentation from docstrings,
and [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/) for styling the pages.

### Setup
```bash
# change the working directory to the python directory
cd ./geoapi/src/main/python

# use venv to create a Python virtual environment in a directory called `.venv`
python3 -m venv .venv

#  activate the virtual environment (linux)
source .venv/bin/activate

# install the needed libraries
pip install -r requirements.txt
```

### Creating the documentation site
[Creating a Materials for MkDocs site](https://squidfunk.github.io/mkdocs-material/creating-your-site/)

Previewing
```bash
mkdocs serve
```

Building
```bash
mkdocs build --clean --strict --verbose --site-dir docs-build
```

### Publishing the documentation site
[Publishing a Materials for MkDocs site](https://squidfunk.github.io/mkdocs-material/publishing-your-site/)
The static pages can be moved to the `snapshot/python` directory of the `gh-pages` branch.
