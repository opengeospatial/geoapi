/*
 * Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 */
package org.opengis.cc;


/**
 * Abstract parameter value or group of parameter values.
 *  
 * @UML abstract CC_GeneralParameterValue
 * @author ISO 19111
 * @author <A HREF="http://www.opengis.org">OpenGIS&reg; consortium</A>
 * @version 2.0
 *
 * @see GeneralOperationParameter
 *
 * @revisit We need a <code>ParameterValue</code> subinterface with a <code>getValue()</code>
 *          method!
 *
 *          We need also some kind of method returning the {@link GeneralOperationParameter}
 *          owner, like {@link ParameterValueGroup#getGroup}.
 */
public interface GeneralParameterValue {
}
