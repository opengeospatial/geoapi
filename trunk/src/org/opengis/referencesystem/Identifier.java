
//  Class:  Identifier
			
package org.opengis.referencesystem;
public interface Identifier		{
		void setCode(String code);
		String getCode();
		void setCodeSpace(String codeSpace);
		String getCodeSpace();
		void setVersion(String version);
		String getVersion();
		void setAuthority(org.opengis.citation.Citation authority);
		org.opengis.citation.Citation getAuthority();
		void setRemarks(String remarks);
		String getRemarks();
		};

		