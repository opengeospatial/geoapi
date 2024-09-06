#
#    GeoAPI - Programming interfaces for OGC/ISO standards
#    Copyright © 2018-2023 Open Geospatial Consortium, Inc.
#    http://www.geoapi.org
#

#
# To run this demo, the PYTHONPATH environmental variable must be set to
# the "geoapi/src/main/python" directory, preferably using absolute path.
# Example on Unix systems executed from the GeoAPI project root directory:
#
#     export GEOAPI_HOME=`pwd`
#     export PYTHONPATH=$GEOAPI_HOME/geoapi/src/main/python
#
# To execute this test on command-line:
#
#     cd $GEOAPI_HOME/geoapi/src/test/python
#     python -m unittest discover
#
# The test in this file is a little bit trivial, but useful for testing
# configuration. More advanced test is available in geoapi-java-python.
#
from opengis.metadata.base           import Metadata
from opengis.metadata.identification import DataIdentification
from opengis.metadata.citation       import Citation
import unittest

#
# Information about a dataset: title, where they are located, authors, etc.
# For this demo, we provide only the title and an abstract. Since this demo
# establishes a one-to-one relationship between data identification and the
# citation (i.e. the title), we implement both interfaces in the same class
# for convenience.
#


class IdentificationMock(DataIdentification, Citation):
    @property
    @abstractmethod
    def citation(self):
        return self

    @property
    @abstractmethod
    def title(self):
        return "Pseudo data"

    @property
    @abstractmethod
    def abstract(self):
        return "A metadata with hard-coded values for demonstration purpose."

    @property
    @abstractmethod
    def language(self):
        return "English"
        # TODO: what is the Python equivalent of java.util.Locale?

#
# The root metadata object. We must provide a body for all methods
# annotated with @abstractproperty in opengis.metadata.base.Metadata.
#


class MetadataMock(Metadata):
    @property
    @abstractmethod
    def identification_info(self):
        return [IdentificationMock()]

    @property
    @abstractmethod
    def contact(self):
        return []

    @property
    @abstractmethod
    def date_info(self):
        return []

#
# We could execute the demo with the following code:
#
#     m = MetadataMock()
#     print(m.identification_info.citation.title)
#
# Instead we run as a unit test, for possible inclusion
# in Maven build. To execute on command-line:
#
#     python -m unittest discover
#
class TestMetadata(unittest.TestCase):
    def test_title(self):
        m = MetadataMock()
        self.assertEqual(m.identification_info[0].citation.title, "Pseudo data")
