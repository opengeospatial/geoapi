package org.opengis.webservice.capability;

// OpenGIS direct dependencies
import org.opengis.webservice.WebServiceRequest;


/**
 * Each OGC Web CapabilitiesService must describe its capabilities. This clause
 * defines the structure intended to convey general information about the
 * service itself, and summary information about the may available data <p/>An
 * instance of <tt>WCSGetCapabilities</tt> encapsulates a GetCapabilites
 * request against a WCS and offeres two factory methods inherited from <tT>
 * GetCapabilities</tt> for request creation using KVP and one own method for
 * request creation from a DOM object.
 *
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth </a>
 */
public interface GetCapabilities extends WebServiceRequest {
    /**
     * Returns the capabilities section.
     */
    String[] getSections();

    /**
     */
    String[] getAcceptVersions();

    /**
     */
    String[] getAcceptFormats();

    /**
     * Returns the update sequence.
     */
    String getUpdateSequence();
}
