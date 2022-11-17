# -*- coding: utf-8 -*-
#
# Configuration file for the Sphinx documentation builder.
# This file contains only a selection of the most common options.
# For a full list see http://www.sphinx-doc.org/en/master/usage/configuration.html
#
# Execution on command-line from the "geoapi" sub-module directory:
#
#     sphinx-build-3 -b html src/main/sphinx target/site/python


# -- Path setup --------------------------------------------------------------
# Directories where to search for extensions or modules to document with autodoc.

import os
import sys
sys.path.insert(0, os.path.abspath('../python'))


# -- Project information -----------------------------------------------------

project    = "GeoAPI"
copyright  = u"1994–2019 Open Geospatial Consortium. All rights reserved"
author     = "Open Geospatial Consortium"
version    = "4.0-SNAPSHOT"
release    = "4.0-SNAPSHOT"
html_title = "Documentation"
html_logo  = "../../../../../site/snapshot/python/static/logo.png"

# -- General configuration ---------------------------------------------------

extensions = [
    'sphinx.ext.autodoc',
    'sphinx.ext.inheritance_diagram'
]

# Since GeoAPI is for many languages, require explicit "py:" prefix for Python.
primary_domain = None

# Show (for instance) "org.metadata.citation" under "c" instead of "o".
modindex_common_prefix = ["opengis.metadata.", "opengis.referencing."]

# The master toctree document.
master_doc = "index"


# -- Options for HTML output -------------------------------------------------

# The theme to use for HTML and HTML Help pages.
# For a list of options available for each theme, see the documentation.
html_theme = "bizstyle"


# -- Options for HTML output -------------------------------------------------

# "¶" for each heading and description that become visible when the mouse hovers over them.
html_add_permalinks = ""

# If true, the reST sources are included in the HTML build as _sources/name.
html_copy_source     = False
html_show_sourcelink = False
html_show_sphinx     = False
