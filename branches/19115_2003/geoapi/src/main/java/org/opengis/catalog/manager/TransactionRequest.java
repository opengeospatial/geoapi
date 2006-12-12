package org.opengis.catalog.manager;

import java.util.List;

import org.opengis.metadata.MetaData;


/**
 * Specification of set of insert, update, and delete actions, plus an optional 
 * identifier. At least one action shall be included. 
 *  
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 *
 */
public interface TransactionRequest {

	List<MetaData> getInsert();
	List<MetaData> getUpdate();
	List<MetaData> getDelete();
 }
