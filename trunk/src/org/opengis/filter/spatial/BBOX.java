package org.opengis.filter.spatial;

public interface BBOX extends SpatialOperator {
    public String getPropertyName();
    public void setPropertyName(String propName);

    public String getSRS();
    public void setSRS(String srs);

    public double getMinX();
    public void setMinX(double minx);

    public double getMinY();
    public void setMinY(double miny);

    public double getMaxX();
    public void setMaxX(double maxx);

    public double getMaxY();
    public void setMaxY(double maxy);
}
