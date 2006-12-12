package org.opengis.catalog.manager;


/**
 * 
 * Total number of records inserted, updated, and deleted 
 *  
 * @author Mauricio Pazos - Axios Engineering
 * @author Gabriel Roldan - Axios Engineering
 *
 */
public interface TransactionSummaryType {

	int getTotalDeleted();
	int getTotalInserted();
	int getTotalUpdated();
}
