<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XML Spy v4.2 U (http://www.xmlspy.com) by John R. Herring (private) -->
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.opengis.net/Java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" version="1.0">
  <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

<!-- NEWLINE = &#xA; -->
<!-- Space = &#x20; -->

<!-- If Public='true' then all attributes and methods will have a visability of public. Notice that the value
is within nested double and single quotes -->
<xsl:variable name="Public" select="'false'"/>

<!-- If Interface='true' then everything is treated as an interface. Notice that the value
is within nested double and single quotes. -->
<xsl:variable name="Interface" select="'false'"/>

<xsl:key name="classes" match="//class | //dataType | //codeList" use="name"/>
<xsl:key name="interfaces" match="//interface" use="name"/>

<xsl:template match="/">
	<xsl:text>
	</xsl:text>
	<xsl:element name="Declarations">
      		<xsl:attribute name="xsi:schemaLocation">http://www.opengis.net/Java.xsd</xsl:attribute>
      		<xsl:call-template name="classList"/>
    	</xsl:element>
</xsl:template>

<xsl:template match="javaprefix"/>
<xsl:template match="predefine"/>
<xsl:template match="packageNameSpace"/>
<xsl:template match="packageName"/>

<!-- classList Template -->
<xsl:template name="classList">
	<xsl:apply-templates/>
</xsl:template>
  	
<xsl:template match="date">
	<xsl:element name="date">
      		<xsl:text>/**  Generated from UML model at DateTime </xsl:text>
      		<xsl:value-of select="."/>
      		<xsl:text>  **/ </xsl:text>
      </xsl:element>
</xsl:template>

<!-- package Template -->
<xsl:template match="package">
	<xsl:element name="package">
		<xsl:for-each select="./packageName">
			<xsl:element name="packageName">
				<xsl:value-of select="."/>
			</xsl:element>
		</xsl:for-each>
		<xsl:for-each select="./javaprefix">
			<xsl:element name="javaPrefix">
				<xsl:value-of select="."/>
			</xsl:element>
		</xsl:for-each>
		
		<!-- don't pull known map types -->
		<xsl:for-each select="class | interface | dataType | codeList">
			<xsl:choose>
				<xsl:when test="name='Double'"/>
				<xsl:when test="name='CharacterString'"/>
				<xsl:when test="name='String'"/>
				<xsl:when test="name='SC_CRS'"/>
				<xsl:when test="name='PositiveInteger'"/>
				<xsl:when test="name='NonNegativeInteger'"/>
				<xsl:when test="name='anyURI'"/>
				<xsl:when test="name='simpleLink'"/>
				<xsl:when test="name='ID'"/>
				<xsl:when test="name='VARCHAR'"/>
				<xsl:when test="name='DateTime'"/>
				<xsl:when test="name='decimal'"/>
				<xsl:when test="name='base64Binary'"/>
				<xsl:when test="name='Boolean'"/>
				<xsl:when test="name='Sign'"/>
				<xsl:when test="name='Vector'"/>
				<xsl:when test="name='Number'"/>
				<xsl:when test="name='Real'"/>
				<xsl:when test="name='Integer'"/>
				<xsl:when test="starts-with(name,'Sequence')"/>
				<xsl:when test="starts-with(name,'Set')"/>
				<xsl:otherwise>
					<xsl:call-template name="classHeader"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:element>
</xsl:template>
<!-- package Template -->

<!-- class header -->
<xsl:template name="classHeader">
	<xsl:variable name="JavaPrefix" select="packageNameSpace"/>
	<xsl:element name="classDefinition">
		<xsl:attribute name="name">
			<xsl:value-of select="name"/>
		</xsl:attribute>
		<xsl:if test="isAbstract">
        		<xsl:attribute name="abstract">true</xsl:attribute>
      		</xsl:if>
      		<xsl:if test="final">
        		<xsl:attribute name="final">true</xsl:attribute>
      		</xsl:if>
      		
      		<xsl:element name="Java">
			
			<xsl:if test="string-length($JavaPrefix)>0">
				<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="$JavaPrefix"/>
					<xsl:text>;&#xA;&#xA;</xsl:text>
			</xsl:if>
		
			
		<!--	********************************************************
 				modification : add import for codeList type
				modified by Stephane, October 22 2003
 				********************************************************-->
			<xsl:choose>
				<xsl:when test="name()='codeList'">
					<xsl:text>//opengis dependencies&#xA;</xsl:text>
					<xsl:text>import org.opengis.lang.CodeList;&#xA;</xsl:text>
				</xsl:when>
			</xsl:choose>
		<!-- end of codeList import section -->
		
		
			<xsl:call-template name="comment">
				<xsl:with-param name="indent" select="''"/>
			</xsl:call-template>
			
			<xsl:call-template name="Visibility"/>
			<xsl:if test="isAbstract">
				<xsl:text> abstract</xsl:text>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="name()='interface' or $Interface='true'">
					<xsl:text> interface </xsl:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:text> class </xsl:text>
				</xsl:otherwise>
			</xsl:choose>
			<xsl:value-of select="name"/>
			
			<!-- apply mapping here? -->

				<xsl:for-each select="superclass[( key('classes', string(name)) and key('classes', string(../name)) )
												or (key('interfaces', string(name)) and key('interfaces', string(../name))) or $Interface='true']
									| specification[( key('classes', string(name)) and key('classes', string(../name)) )
												or (key('interfaces', string(name)) and key('interfaces', string(../name))) or $Interface='true']">
				
					<xsl:if test="position()=1">
						<xsl:text> extends </xsl:text>
					</xsl:if>
					<xsl:if test="packageNameSpace">
						<xsl:value-of select="packageNameSpace"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
					<xsl:call-template name="MapType">
						<xsl:with-param name="type" select="name"/>
						<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
					</xsl:call-template>
					<xsl:if test="position()!=last()">, </xsl:if>

				</xsl:for-each>
			
				
		<!--	********************************************************
				modification : add extends for codeList type
				modified by Stephane, October 22 2003
				********************************************************-->
				<xsl:choose>
				<xsl:when test="name()='codeList'">
					<xsl:text>&#x20;extends CodeList</xsl:text>
				</xsl:when>
			</xsl:choose>
		<!-- end of extends CodeList section -->
			
			
			
				<xsl:for-each select="superclass[key('interfaces', string(name)) and key('classes', string(../name)) and $Interface!='true']											 | specification[key('interfaces', string(name)) and key('classes', string(../name)) and $Interface!='true']">
					<xsl:if test="position()=1">
						<xsl:text> implements </xsl:text>
					</xsl:if>
					<xsl:if test="packageNameSpace">
						<xsl:value-of select="packageNameSpace"/>
						<xsl:text>.</xsl:text>
					</xsl:if>
					<xsl:call-template name="MapType">
						<xsl:with-param name="type" select="name"/>
						<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
					</xsl:call-template>
					<xsl:if test="position()!=last()">, </xsl:if>
				</xsl:for-each>
				
			<xsl:text>&#xA;{&#xA;</xsl:text>
			<xsl:choose>
				<xsl:when test="name()='interface' or $Interface='true'">
					<xsl:call-template name="interface">
						<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="name()='class'">
					<xsl:call-template name="class">
						<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="name()='dataType'">
					<xsl:call-template name="dataType">
						<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
					</xsl:call-template>
				</xsl:when>
				
				<xsl:when test="name()='codeList'">
					<xsl:call-template name="codeList"/>
				</xsl:when>
			</xsl:choose>
		<xsl:text>}&#xA;</xsl:text>
		</xsl:element>
	</xsl:element>
</xsl:template>

<!-- codeList Template -->
<!-- <xsl:template name="codeList">
old xsl code : codeList Template
       <xsl:text>    private static String[] values = {</xsl:text>
        <xsl:call-template name="AttributesForEnumType"/>
        <xsl:text>};&#xA;</xsl:text>
        <xsl:text>    public int code;&#xA;</xsl:text>
        <xsl:text>    public String value() { return values[code];}&#xA;</xsl:text>
</xsl:template>
-->

<!--	********************************************************
 		modification of codeList Template
		modified by Stephane, October 22 2003
 		********************************************************-->
<xsl:template name="codeList">
	<xsl:param name="JavaPrefix"/>
	<xsl:param name="name"/>
    <xsl:apply-templates select="attribute">
    	<xsl:with-param name="className" select="name" />
    </xsl:apply-templates>
    <xsl:text>&#xA;</xsl:text>
    <xsl:text>    private </xsl:text>
    <xsl:value-of select="name"/>
    <xsl:text>(int ordinal, String name)&#xA;</xsl:text>
    <xsl:text>    {&#xA;</xsl:text>
    <xsl:text>        super(ordinal, name);&#xA;</xsl:text>
    <xsl:text>    }&#xA;</xsl:text>
    <xsl:call-template name="Method">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
</xsl:template>
<!-- end of codeList Template section -->


<!-- class -->
<xsl:template name="class">
	<xsl:param name="JavaPrefix"/>
	<xsl:call-template name="AttributesForClassType">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
	<xsl:call-template name="Method">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
  </xsl:template>
  
<!-- interface -->
<xsl:template name="interface">
	<xsl:param name="JavaPrefix"/>
	<xsl:call-template name="Method">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
	<xsl:call-template name="AttributesForComplexType">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
</xsl:template>
  
<!-- dataType -->
<xsl:template name="dataType">
	<xsl:param name="JavaPrefix"/>
	<xsl:call-template name="AttributesForClassType">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
	<xsl:call-template name="AttributesForComplexType">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
	<xsl:call-template name="Method">
		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
	</xsl:call-template>
</xsl:template>
  
<!-- AttributesForComplexType -->
  <xsl:template name="AttributesForComplexType">
  	<xsl:param name="JavaPrefix"/>
    <xsl:for-each select="attribute">
      <xsl:call-template name="GetSetAttributeDefinition">
      		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      </xsl:call-template>
    </xsl:for-each>
  </xsl:template>
  
  
<!-- AttributesForClassType -->
  <xsl:template name="AttributesForClassType">
  	<xsl:param name="JavaPrefix"/>
    <xsl:for-each select="attribute">
      <xsl:call-template name="AttributeDefinition">
      		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      </xsl:call-template>
    </xsl:for-each>
  </xsl:template>
  
<!-- GetSetAttributeDefinition (change by James MacGill, PSU) -->
<xsl:template name="GetSetAttributeDefinition">
	<xsl:param name="JavaPrefix"/>
	<xsl:variable name="type">
		<xsl:call-template name="MapType">
      			<xsl:with-param name="type" select="type"/>
      			<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      		</xsl:call-template>
	</xsl:variable>
	<xsl:variable name="upperCase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
	<xsl:variable name="lowerCase" select="'abcdefghijklmnopqrstuvwxyz'"/>
    	<xsl:if test="visibility='public' or $Public='true'">
      		<xsl:text>    public void</xsl:text>
	      	<xsl:text> </xsl:text>
	      <xsl:text>set</xsl:text>
	      <xsl:value-of select="translate((substring(name, 1,1) ), $lowerCase, $upperCase)"/>
	      <xsl:value-of select="substring(name, 2) "/>
	      <xsl:text>(</xsl:text>
	      <xsl:for-each select="packageNameSpace">
			<xsl:value-of select="."/>
			<xsl:text>.</xsl:text>
		</xsl:for-each>
		<xsl:value-of select="$type"/>
	      <xsl:text> </xsl:text>
	      <xsl:value-of select="name"/>
	      	<xsl:if test="string-length(cardinality)>0">
			<xsl:text>[]</xsl:text>
		</xsl:if>
	      <xsl:text>)</xsl:text>
		<xsl:call-template name="MethodBody">
			<xsl:with-param name="returnType" select="'void'"/>
		</xsl:call-template>
		
		<xsl:text>    public </xsl:text>
	    	<xsl:for-each select="packageNameSpace">
	      		<xsl:value-of select="."/>
	      		<xsl:text>.</xsl:text>
	    	</xsl:for-each>
	    	<xsl:value-of select="$type"/>
	    	<xsl:if test="string-length(cardinality)>0">
			<xsl:text>[]</xsl:text>
		</xsl:if>
	    	<xsl:text> </xsl:text>
	    	<xsl:text>get</xsl:text>
	    	<xsl:value-of select="translate((substring(name, 1,1) ), $lowerCase, $upperCase)"/>
	    	<xsl:value-of select="substring(name, 2) "/>
	    	<xsl:text>()</xsl:text>
	    	<xsl:call-template name="MethodBody">
	    		<xsl:with-param name="returnType" select="$type"/>
	    	</xsl:call-template>
	 </xsl:if>
</xsl:template>
  
<!-- AttributeDefinition -->
<xsl:template name="AttributeDefinition">
	<xsl:param name="JavaPrefix"/>
	<xsl:call-template name="comment">
		<xsl:with-param name="indent" select="'    '"/>
	</xsl:call-template>
	<xsl:text>    </xsl:text>
	<xsl:call-template name="Visibility"/>
	<xsl:text> </xsl:text>
	<xsl:call-template name="MapType">
      		<xsl:with-param name="type" select="type"/>
      		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      	</xsl:call-template>
	<xsl:text> </xsl:text>
	<xsl:value-of select="name"/>
	<xsl:if test="string-length(cardinality)>0">
		<xsl:text>[]</xsl:text>
	</xsl:if>
	<xsl:text>;
</xsl:text>
</xsl:template>


<!-- AttributesForEnumType -->
  <xsl:template name="AttributesForEnumType">
    <xsl:for-each select="attribute">
      <xsl:call-template name="EnumAttributeDefinition"/>
    </xsl:for-each>
  </xsl:template>

  
<!--	********************************************************
 		modification of AttributesForEnumType for type codeList
		modified by Stephane, October 23 2003
 		********************************************************-->
  <xsl:template match="attribute">
  	<xsl:param name="className"/>
  	<xsl:variable name="indice">
    	<xsl:number level="any" from="codeList"/>
    </xsl:variable>
    <xsl:text>    public static final </xsl:text>
    <xsl:call-template name="UppercaseName">
    	<xsl:with-param name="attributeName" select="name"/>
    </xsl:call-template>
    <xsl:text> = new </xsl:text>
    <xsl:value-of select="$className"/>
    <xsl:text>(</xsl:text>
    <xsl:value-of select="$indice - 1"/>
    <xsl:text> , "</xsl:text>
    <xsl:value-of select="name"/>
    <xsl:text>" );&#xA;</xsl:text>
  </xsl:template>

<!--	*****************************************************************
		added for use with previous modification on AttributesForEnumType
		added by Stephane, October 23 2003
		***************************************************************** -->
<xsl:template name="UppercaseName">
	<xsl:param name="attributeName"/>
	<xsl:variable name="upperCase" select="'_ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
	<xsl:variable name="lowerCase" select="' abcdefghijklmnopqrstuvwxyz'"/>
	<xsl:value-of select="translate($attributeName, $lowerCase, $upperCase)"/>
</xsl:template>

<!-- end of section attribute for codeList type  -->


  
<!-- EnumAttributeDefinition -->
  <xsl:template name="EnumAttributeDefinition">
  	<xsl:param name="JavaPrefix"/>
    <xsl:text>&quot;</xsl:text>
    <xsl:value-of select="name"/>
    <xsl:text>&quot;</xsl:text>
    <xsl:if test="position()!=last()">, </xsl:if>
  </xsl:template>

<!-- Method Template -->
<xsl:template name="Method">
	<xsl:param name="JavaPrefix"/>
    	<xsl:for-each select="method">
    	
    	<xsl:call-template name="comment">
    		<xsl:with-param name="indent" select="'    '"/>
    	</xsl:call-template>
    	
    	<xsl:variable name="returnType">
		<xsl:choose>
			<xsl:when test="returnType">
				<xsl:call-template name="MapType">
      					<xsl:with-param name="type" select="returnType/type"/>
      					<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:text>void</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:variable>
	
      	<xsl:text>    </xsl:text>
      	<xsl:call-template name="Visibility"/>
      	<xsl:text> </xsl:text>
        	<xsl:if  test="returnType">
          		<xsl:for-each select="returnType/packageNameSpace">
            			<xsl:value-of select="."/>
            			<xsl:text>.</xsl:text>
          		</xsl:for-each>
          	</xsl:if>
       <xsl:value-of select="$returnType"/>
      	<xsl:text> </xsl:text>
      	<xsl:value-of select="methodName"/>
      	<xsl:text> (</xsl:text>
      	<xsl:call-template name="Parameter">
      		<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      	</xsl:call-template>
      	<xsl:text>)</xsl:text>
      <xsl:call-template name="MethodBody">
      		<xsl:with-param name="returnType" select="$returnType"/>
      	</xsl:call-template>
    	</xsl:for-each>
</xsl:template>
<!-- Method Template -->

<!-- Parameter Template -->
<xsl:template name="Parameter">
	<xsl:param name="JavaPrefix"/>
    	<xsl:for-each select="parameter">
      		<xsl:if test="packageNameSpace">
	        	<xsl:value-of select="packageNameSpace"/>
	       	<xsl:text>.</xsl:text>
	      	</xsl:if>
	      	<xsl:call-template name="MapType">
      			<xsl:with-param name="type" select="parameterType"/>
      			<xsl:with-param name="JavaPrefix" select="$JavaPrefix"/>
      		</xsl:call-template>
	      	<xsl:text>  </xsl:text>
	      	<xsl:value-of select="parameterName"/>
	      	<xsl:if test="isArray">
	      		<xsl:text>[]</xsl:text>
	      	</xsl:if>
		<xsl:if test="position()!=last()">, </xsl:if>
    	</xsl:for-each>
</xsl:template>

<!-- MapType -->
<xsl:template name="MapType">
	<xsl:param name="type"/>
	<xsl:param name="JavaPrefix"/>
	<xsl:choose>
		<xsl:when test="$type='Double'">
			<xsl:text>double</xsl:text>
		</xsl:when>
		<xsl:when test="$type='CharacterString'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='SC_CRS'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='PositiveInteger'">
			<xsl:text>long</xsl:text>
		</xsl:when>
		<xsl:when test="$type='NonNegativeInteger'">
			<xsl:text>long</xsl:text>
		</xsl:when>
		<xsl:when test="$type='anyURI'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='simpleLink'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='ID'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='VARCHAR'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='DateTime'">
			<xsl:text>String</xsl:text>
		</xsl:when>
		<xsl:when test="$type='decimal'">
			<xsl:text>double</xsl:text>
		</xsl:when>
		<xsl:when test="$type='base64Binary'">
			<xsl:text>byte[]</xsl:text>
		</xsl:when>
		<xsl:when test="$type='Boolean'">
			<xsl:text>boolean</xsl:text>
		</xsl:when>
		<xsl:when test="$type='Sign'">
			<xsl:text>boolean</xsl:text>
		</xsl:when>
		<xsl:when test="$type='Number'">
			<xsl:text>double</xsl:text>
		</xsl:when>
		<xsl:when test="$type='Real'">
			<xsl:text>double</xsl:text>
		</xsl:when>
		<xsl:when test="$type='Integer'">
			<xsl:text>int</xsl:text>
		</xsl:when>
		<xsl:when test="starts-with($type, 'Sequence')">
			<xsl:text>java.util.Vector</xsl:text>
			<xsl:call-template name="HandleBraces">
				<xsl:with-param name="name" select="$type"/>
			</xsl:call-template>
		</xsl:when>
		<xsl:when test="starts-with($type, 'Set')">
			<xsl:text>java.util.Vector</xsl:text>
			<xsl:call-template name="HandleBraces">
				<xsl:with-param name="name" select="$type"/>
			</xsl:call-template>
		</xsl:when>
		<xsl:otherwise>
			<!-- verify the prefixes are the same, otherwise prepend $JavaPrefix -->
			<xsl:variable name="CurJavaPrefix">
				<xsl:choose>
					<xsl:when test="key('classes', $type)">
						<xsl:value-of select="key('classes', $type)/packageNameSpace"/>
					</xsl:when>
					<xsl:when test="key('interfaces', $type)">
						<xsl:value-of select="key('interfaces', $type)/packageNameSpace"/>
					</xsl:when>
				</xsl:choose>
			</xsl:variable>
					
			<xsl:if test="string-length($CurJavaPrefix)>0 and $CurJavaPrefix!=$JavaPrefix">
				<xsl:value-of select="$CurJavaPrefix"/>
				<xsl:text>.</xsl:text>
			</xsl:if>
			<xsl:value-of select="$type"/>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- HandleAngles -->
<xsl:template name="HandleBraces">
	<xsl:param name="name"/>
	<xsl:if test="contains($name, '&lt;')">
		<xsl:text> /*</xsl:text>
		<xsl:value-of select="substring-before(substring-after($name, '&lt;'), '&gt;')"/>
		<xsl:text>*/</xsl:text>
	</xsl:if>
</xsl:template>

<!-- Visibility -->
<xsl:template name="Visibility">
	<xsl:choose>
		<xsl:when test="$Public='true'">
			<xsl:text>Public</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:value-of select="visibility"/>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- MethodBody-->
<xsl:template name="MethodBody">
	<xsl:param name="returnType"/>
	<xsl:choose>
		<xsl:when test="key('interfaces', string(../name)) or $Interface='true'">
			<xsl:text>;&#xA;</xsl:text>
		</xsl:when>
		<xsl:otherwise>
			<xsl:text> { </xsl:text>
			<xsl:if test="$returnType!='void'">
				<xsl:text>return </xsl:text>
				<xsl:choose>
					<xsl:when test="string-length(cardinality)>0">
						<xsl:text>null</xsl:text>
					</xsl:when>
					<xsl:when test="$returnType='int' or $returnType='byte' or $returnType='short' or $returnType='long' or $returnType='float' or $returnType='double' or $returnType='char' ">
						<xsl:text>0</xsl:text>
					</xsl:when>
					<xsl:when test="$returnType='boolean'">
						<xsl:text>false</xsl:text>
					</xsl:when>
					<xsl:otherwise>
						<xsl:text>null</xsl:text>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:text>;</xsl:text>
			</xsl:if>
			<xsl:text> }&#xA;</xsl:text>
		</xsl:otherwise>
	</xsl:choose>
</xsl:template>

<!-- Comment -->
<xsl:template name="comment">
	<xsl:param name="indent"/>
	<xsl:if test="comment">
		<xsl:text>&#xA;</xsl:text>	
		<xsl:value-of select="$indent"/>
		<xsl:text>/**&#xA;</xsl:text>	
		<xsl:call-template name="subComment">
			<xsl:with-param name="comment" select="concat(normalize-space(comment), ' ')"/>
			<xsl:with-param name="lineLength" select="80"/>
			<xsl:with-param name="indent" select="$indent"/>
			<xsl:with-param name="pos" select="1"/>
		</xsl:call-template>
		<xsl:value-of select="$indent"/>
		<xsl:text> */&#xA;</xsl:text>
	</xsl:if>
</xsl:template>

<!-- SubComment -->
<xsl:template name="subComment">
	<xsl:param name="comment"/>
	<xsl:param name="lineLength"/>
	<xsl:param name="indent"/>
	<xsl:param name="pos"/>
	<xsl:if test="$pos &lt; string-length($comment)">
		<xsl:value-of select="$indent"/>
		<xsl:text> * </xsl:text>
		
		<xsl:value-of select="substring($comment, $pos, $lineLength)"/>
		<xsl:variable name="lastWord" select="substring-before(substring($comment, $pos + $lineLength), ' ')"/>
		<xsl:value-of select="$lastWord"/>
		
		<xsl:text>&#xA;</xsl:text>
		<xsl:call-template name="subComment">
			<xsl:with-param name="comment" select="$comment"/>
			<xsl:with-param name="lineLength" select="$lineLength"/>
			<xsl:with-param name="indent" select="$indent"/>
			<xsl:with-param name="pos" select="$pos + $lineLength + string-length($lastWord) + 1"/>
		</xsl:call-template>
	</xsl:if>
</xsl:template>

<!--======================================-->
<!-- After the Steve changes, none of this is used -->
<!--=====================================-->

<!-- union Template -->
  <xsl:template name="union">
    <xsl:comment> ================== union ==================== </xsl:comment>
    <xsl:element name="classDefinition">
      <xsl:attribute name="name">
        <xsl:value-of select="name"/>
      </xsl:attribute>
      <xsl:if test="isAbstract">
        <xsl:attribute name="abstract">true</xsl:attribute>
      </xsl:if>
      <xsl:if test="final">
        <xsl:attribute name="final">true</xsl:attribute>
      </xsl:if>
      <xsl:call-template name="Doc"/>
      <xsl:element name="Java">
        <xsl:for-each select="packageNameSpace">
          <xsl:variable name="packageNameSpace" select="."/>
          <xsl:if test="string-length($packageNameSpace)>0">
            <xsl:text>
package </xsl:text>
            <xsl:value-of select="."/>
            <xsl:text>;</xsl:text>
          </xsl:if>
        </xsl:for-each>
        <xsl:text>
enum </xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>Enum { 
	</xsl:text>
        <xsl:call-template name="AttributesForUnionEnumType"/>
        <xsl:text>
	  }; </xsl:text>
        <xsl:text>
</xsl:text>
        <xsl:text>
union  </xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>  switch (</xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>Enum)  {
</xsl:text>
        <xsl:call-template name="AttributesForUnionType"/>
        <xsl:text> 		};
</xsl:text>
      </xsl:element>
    </xsl:element>
  </xsl:template>
<!-- union Template -->

<!-- enumeration Template -->
  <xsl:template name="enumeration">
	<xsl:call-template name="classHeader"/>
        <xsl:text> private static String[] values = {</xsl:text>
        <xsl:text>
</xsl:text>
        <xsl:call-template name="AttributesForEnumType"/>
        <xsl:text>
									}; 
</xsl:text>
        <xsl:text> 		public int code;
</xsl:text>
        <xsl:text> 		public String value() { return values[code];}
</xsl:text>
        <xsl:text>	 }; 
</xsl:text>
  </xsl:template>
<!-- enumeration Template -->

<!-- measure Template -->
  <xsl:template name="measure">
        <xsl:text> 		 		public </xsl:text>
        <xsl:value-of select="type"/>
        <xsl:text> value;
</xsl:text>
        <xsl:text> 		 		public String unit;
</xsl:text>
</xsl:template>
<!-- measure Template -->
  
<!-- EnumRefAttributeDefinition-->
  <xsl:template name="EnumRefAttributeDefinition">
    <xsl:choose>
      <xsl:when test="name = 'indirect'"/>
      <xsl:otherwise>
        <xsl:text> e_</xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>Ref,</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
<!-- UnionEnumAttributeDefinition-->
  <xsl:template name="UnionEnumAttributeDefinition">
    <xsl:choose>
      <xsl:when test="name = 'indirect'"/>
      <xsl:otherwise>
        <xsl:text>  </xsl:text>
        <xsl:value-of select="name"/>
        <xsl:if test="position()!=last()">, </xsl:if>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
<!--  Doc-->
  <xsl:template name="Doc"/>
  
<!--  AttributesForUnionType -->
  <xsl:template name="AttributesForUnionType">
    <xsl:if test="attribute/name = 'indirect'">
      <xsl:for-each select="attribute">
        <xsl:call-template name="UnionRefAttributeDefinition"/>
      </xsl:for-each>
    </xsl:if>
    <xsl:for-each select="attribute">
      <xsl:call-template name="UnionAttributeDefinition"/>
    </xsl:for-each>
  </xsl:template>
  
<!--  UnionAttributeDefinition  -->
  <xsl:template name="UnionAttributeDefinition">
    <xsl:choose>
      <xsl:when test="name = 'indirect'"/>
      <xsl:otherwise>
        <xsl:text>		case e_</xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>: </xsl:text>
        <xsl:value-of select="type"/>
        <xsl:text> </xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>;
</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
<!--  UnionRefAttributeDefinition  -->
  <xsl:template name="UnionRefAttributeDefinition">
    <xsl:choose>
      <xsl:when test="name = 'indirect'"/>
      <xsl:otherwise>
        <xsl:text>		case e_</xsl:text>
        <xsl:value-of select="name"/>
        <xsl:text>_ptr:  </xsl:text>
        <xsl:value-of select="type"/>
        <xsl:text>_ptr </xsl:text>
        <xsl:value-of select="type"/>
        <xsl:text>_ptr;
</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
  
<!--  AttributesForUnionEnumType -->
  <xsl:template name="AttributesForUnionEnumType">
    <xsl:if test="attribute/name = 'indirect'">
      <xsl:for-each select="attribute">
        <xsl:call-template name="EnumRefAttributeDefinition"/>
      </xsl:for-each>
    </xsl:if>
    <xsl:for-each select="attribute">
      <xsl:call-template name="UnionEnumAttributeDefinition"/>
    </xsl:for-each>
</xsl:template>

<!-- constructor Template -->
  <xsl:template name="Constructor">
    <xsl:for-each select="constructor">
      <xsl:text>		</xsl:text>
<!--			<xsl:text>static </xsl:text> -->
      <xsl:choose>
        <xsl:when test="returnType">
          <xsl:for-each select="returnType/packageNameSpace">
            <xsl:value-of select="."/>
            <xsl:text>.</xsl:text>
          </xsl:for-each>
          <xsl:value-of select="returnType/type"/>
        </xsl:when>
        <xsl:otherwise>
          <xsl:text>void</xsl:text>
        </xsl:otherwise>
      </xsl:choose>
      <xsl:if test="returnType"/>
      <xsl:text>  </xsl:text>
      <xsl:value-of select="methodName"/>
      <xsl:text> (</xsl:text>
      <xsl:call-template name="Parameter"/>
      <xsl:text> );
</xsl:text>
    </xsl:for-each>
  </xsl:template>
<!-- constructor Template -->
 
</xsl:transform>
