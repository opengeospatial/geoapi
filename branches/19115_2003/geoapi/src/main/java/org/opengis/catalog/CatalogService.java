package org.opengis.catalog;

// OpenGIS dependencies
import org.opengis.catalog.discovery.Discovery;
import org.opengis.catalog.manager.Manager;

// TODO agregar las anotaciones
//import org.opengis.annotation.UML;
//import static org.opengis.annotation.Obligation.*;
//import static org.opengis.annotation.Specification.*;

/**
 * The catalog service is an implementation of the OGC Catalogue Service for Web
 * (CSW) version 2.0.1
 * 
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 */
public interface CatalogService extends OGC_Service {

    String getVersion();

    /**
     * Returns the service type (CSW).
     */
    String getServiceTypeId();

    /**
     * Returns the discovery.
     */
    // @UML(identifier="discovery", specification=UNSPECIFIED)
    Discovery getDiscovery();

    /**
     * Returns the manager.
     */
    // @UML(identifier="manager", specification=UNSPECIFIED)
    Manager getManager();
}
