
package org.opengis.coverage;

import java.util.List;

/**
 * <p></p>
 * 
 * This exception is thrown as a warning to users that 
 * the last element of result list of a find operation is 
 * the same distance as other objects not selected.
 * @author Alexander Petkov
 */
public class TruncatedResultException extends Exception {

/**
 * <p>Represents result list of a find operation.</p>
 * 
 */
    private List<? extends GeometryValuePair> resultList;

/**
 * Returns the result list of a find operation.
 * @return resultList
 */
    public List<? extends GeometryValuePair> getResultList() {        
        return resultList;
    } 

/**
 * Sets the result list.
 * @param _resultList 
 */
    public void setResultList(List<? extends GeometryValuePair> _resultList) {        
        resultList = _resultList;
    } 
 }
