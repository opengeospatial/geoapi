package org.opengis.feature.simple;

import java.util.Date;

import org.opengis.feature.Attribute;
import org.opengis.feature.type.AttributeType;

/**
 * Attribute containing a date value: Attribute<T extends AttributeType<Date>>
 * 
 * @author Justin Deoliveira, The Open Planning Project, jdeolive@openplans.org
 *
 */
public interface TemporalAttribute<T extends AttributeType<Date>> extends Attribute<Date,T> {

}
