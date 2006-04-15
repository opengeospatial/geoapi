/*----------------    FILE HEADER  ------------------------------------------

This file is part of deegree.
Copyright (C) 2001 by:
EXSE, Department of Geography, University of Bonn
http://www.giub.uni-bonn.de/exse/
lat/lon Fitzke/Fretter/Poth GbR
http://www.lat-lon.de

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

Contact:

Andreas Poth
lat/lon Fitzke/Fretter/Poth GbR
Meckenheimer Allee 176
53115 Bonn
Germany
E-Mail: poth@lat-lon.de

Jens Fitzke
Department of Geography
University of Bonn
Meckenheimer Allee 166
53115 Bonn
Germany
E-Mail: jens.fitzke@uni-bonn.de

                 
 ---------------------------------------------------------------------------*/
package org.opengis.webservice.capability;

// OpenGIS direct dependencies
import org.opengis.util.InternationalString;
import org.opengis.metadata.citation.Contact;
import org.opengis.metadata.citation.OnLineResource;
import org.opengis.metadata.identification.Keywords;
import org.opengis.metadata.constraint.Constraints;

// Annotations
import org.opengis.annotation.UML;
import static org.opengis.annotation.Obligation.*;
import static org.opengis.annotation.Specification.*;


/**
 * Provides acces to the &lt;CapabilitiesService&gt; element of the  Capabilities XML
 * providing general metadata for the service as a whole. It shall include a
 * Name, Title, and Online Resource URL. Optionally, Abstract, Keyword List,
 * Contact Information, Fees, and Access Constraints may be provided. The meaning
 * of most of these elements is defined in [ISO 19115]. The CapabilitiesService Name shall
 * be "OGC:WMS" in the case of a Web Map CapabilitiesService.
 *
 * @author <a href="mailto:k.lupp@web.de">Katharina Lupp</a>
 * @author <a href="mailto:mschneider@lat-lon.de">Markus Schneider</a>
 */
public interface CapabilitiesService {
    /**
     * Returns the name of the service. Typically, the Name is a single word used
     * for machine-to-machine communication.
     */
    @UML(identifier="name", specification=UNSPECIFIED)
    String getName();

    /**
     * Returns the title of the service. The Title is for the benefit of humans.
     * The CapabilitiesService Title is at the discretion of the provider, and should be
     * brief yet descriptive enough to identify this server in a menu with other
     * servers.
     *
     * @see #getName()
     */
    @UML(identifier="title", specification=UNSPECIFIED)
    InternationalString getTitle();

    /**
     * The Abstract element allows a descriptive narrative providing more
     * information about the enclosing object.
     */
    InternationalString getAbstract();

    /**
     * A list of keywords or keyword phrases should be included to help catalog
     * searching.
     */
    @UML(identifier="keywordList", specification=UNSPECIFIED)
    Keywords getKeywords();

    /**
     * The OnlineResource element within the CapabilitiesService element can be used, for
     * example, to point to the web site of the service provider. There are other
     * OnlineResource elements used for the URL prefix of each supported operation.
     */
    @UML(identifier="onlineResource", specification=UNSPECIFIED)
    OnLineResource getOnlineResource();

    /**
     * Returns informations who to contact for questions about the service. This
     * method returns <tt>null</tt> if no contact informations are available.
     */
    @UML(identifier="contactInformation", specification=UNSPECIFIED)
    Contact getContactInformation();

    /**
     * Returns fees assigned to the service. If no fees defined "none" will be
     * returned.
     */
    @UML(identifier="fees", specification=UNSPECIFIED)
    InternationalString getFees();

    /**
     * Returns access constraints assigned to the service. If no access
     * constraints are defined {@code null} will be returned.
     */
    @UML(identifier="accessConstraints", specification=UNSPECIFIED)
    Constraints getAccessConstraints();
}
