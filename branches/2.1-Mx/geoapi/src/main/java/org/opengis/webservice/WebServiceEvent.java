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
package org.opengis.webservice;

// J2SE dependencies
import java.util.EventObject;


/**
 * This is the defining interface for event objects that contains a request,
 * a response that should be made available for a service.
 * <p>
 * the kind of contained imformation can be determined by calling the
 * {@link #getType} method.
 * 
 * @author <a href="mailto:poth@lat-lon.de">Andreas Poth</a>
 * @version 2002-04-16
 * @deprecated Legacy code of deegree 1.x.
 */
public final class WebServiceEvent extends EventObject {
    
    public static final int REQUEST = 0;
    public static final int RESPONSE = 1;
    
    private final WebServiceClient client;
    private final WebServiceRequest request;
    private final WebServiceResponse response;
    private final String id;
    private final int type;
    private final String message;

    /**
     * Creates a new WebServiceEvent object.
     *
     * @param source 
     * @param request 
     * @param message 
     */
    public WebServiceEvent(WebService source, WebServiceRequest request, String message) {
        super(source);
        this.client   = null;
        this.request  = request;
        this.response = null;
        this.id       = request.getId();
        this.type     = REQUEST;
        this.message  = message;
    }

    /**
     * Creates a new WebServiceEvent object.
     *
     * @param source 
     * @param request 
     * @param message 
     * @param client 
     */
    public WebServiceEvent(WebService source, WebServiceRequest request, String message, WebServiceClient client) {
        super(source);
        this.client   = client;
        this.request  = request;
        this.response = null;
        this.id       = request.getId();
        this.type     = REQUEST;
        this.message  = message;
    }

    /**
     * Creates a new WebServiceEvent object.
     *
     * @param source 
     * @param response 
     * @param message 
     */
    public WebServiceEvent(WebService source, WebServiceResponse response, String message) {
        super(source);
        this.client   = null;
        this.request  = null;
        this.response = response;
        this.id       = (response.getRequest() != null) ? response.getRequest().getId() : "";
        this.type     = RESPONSE;
        this.message  = message;
    }

    /**
     * Returns the id of the of the request which performance caused the
     * event.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the type of event. possible values are:
     * <ul>
     * <li>REQUSET
     * <li>RESPONSE
     * <li>MESSAGE
     * <li>EXCEPTION
     * </ul>
     * An EXCEPTION will allways be a response to a request or a message.
     */
    public int getType() {
        return type;
    }

    /**
     * If the event is a REQUEST type the method returns the request transported
     * by the event. otherwise <tt>null</tt> will be returned.
     */
    public WebServiceRequest getRequest() {
        return request;
    }

    /**
     * If the event is a RESPONSE type the method returns the response transported
     * by the event. otherwise <tt>null</tt> will be returned.
     */
    public WebServiceResponse getResponse() {
        return response;
    }

    /**
     * Returns the instance of the <tt>OGCWebService</tt> that is the source of
     * the event.
     */
    public WebService getEventSource() {
        return (WebService)super.getSource();
    }

    /**
     * Returns the client where to write the result/response or an
     * error message to
     */
    public WebServiceClient getDestination() {
        return client;
    }
    
    public String getMessage(){
    	return message;
    }

    @Override
    public String toString() {
        String ret = null;
        ret = getClass().getName() + ":\n";
        ret += ( "request = " + request + "\n" );
        ret += ( "response = " + response + "\n" );
        ret += ( "client = " + client + "\n" );
        ret += ( "type = " + type + "\n" );
        ret += ( "message = " + message + "\n" );
        return ret;
    }
}
