/*
 * Created on Aug 5, 2004
 */
package org.opengis.sld;

/**
 * @author cdillard
 */
public interface ExternalGraphic {
    public String getOnlineResource();
    public void setOnlineResource(String url);

    public String getFormat();
    public void setFormat(String format);
}
