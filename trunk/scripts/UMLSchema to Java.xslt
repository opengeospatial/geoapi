<?xml version="1.0" encoding="UTF-8"?>
<!-- edited with XML Spy v4.2 U (http://www.xmlspy.com) by John R. Herring (private) -->
<xsl:transform version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="http://www.opengis.net/Java" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output method="xml" indent="yes" encoding="UTF-8"/>
	<!-- NEWLINE = &#xA; -->
	<!-- Space = &#x20; -->
	<xsl:template match="/">
		<xsl:text>&#xA;</xsl:text>
		<xsl:element name="Declarations">
			<xsl:attribute name="xsi:schemaLocation">http://www.opengis.net/Java.xsd</xsl:attribute>
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<xsl:template match="javaprefix"/>
	<xsl:template match="predefine"/>
	<xsl:template match="packageNameSpace"/>
	<xsl:template match="packageName"/>
	<!-- classList Template -->
	<xsl:template match="classList">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="date">
		<xsl:element name="date">
			<xsl:text>/**  Generated from UML model at DateTime </xsl:text>
			<xsl:value-of select="."/>
			<xsl:text>  **/ &#xA;</xsl:text>
		</xsl:element>
	</xsl:template>
	<!-- classList Template -->
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
			<xsl:apply-templates/>
		</xsl:element>
	</xsl:template>
	<!-- package Template -->
	<!-- union Template -->
	<xsl:template match="union">
		<xsl:call-template name="Separator"/>
		<xsl:comment> ================== union ==================== </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:if test="isAbstract">
				<xsl:attribute name="abstract">true</xsl:attribute>
			</xsl:if>
			<xsl:if test="final">
				<xsl:attribute name="final">true</xsl:attribute>
			</xsl:if>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;enum </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>Enum { 
					</xsl:text>
				<xsl:call-template name="AttributesForUnionEnumType"/>
				<xsl:text>
					}; </xsl:text>
				<xsl:text>&#xA;</xsl:text>
				<xsl:text>&#xA;union  </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>  switch (</xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>Enum)  {&#xA;</xsl:text>
				<xsl:call-template name="AttributesForUnionType"/>
				<xsl:text> 		};&#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- union Template -->
	<!-- enumeration Template -->
	<xsl:template match="enumeration">
		<xsl:comment> ================== enumeration ==================== </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;public final class </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text> { &#xA; 		</xsl:text>
				<xsl:text> private static String[] values = {</xsl:text>
				<xsl:text>&#xA;</xsl:text>
				<xsl:call-template name="AttributesForEnumType"/>
				<xsl:text>&#xA;									}; &#xA;</xsl:text>
				<xsl:text> 		public int code;&#xA;</xsl:text>
				<xsl:text> 		public String value() { return values[code];}&#xA;</xsl:text>
				<xsl:text>	 }; &#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- enumeration Template -->
	<!-- codeList Template -->
	<xsl:template match="codeList">
		<xsl:comment> ================== codeList ==================== </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;public class </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text> extends Object&#xA;</xsl:text>
				<xsl:text> 		 		{ &#xA;</xsl:text>
				<xsl:text> 		 		private static String[] values = {</xsl:text>
				<xsl:text>&#xA; 		 		</xsl:text>
				<xsl:call-template name="AttributesForEnumType"/>
				<xsl:text>	 		 		}; &#xA;</xsl:text>
				<xsl:text> 		 		public int code;&#xA;</xsl:text>
				<xsl:text> 		 		public String value() { return values[code];}&#xA;</xsl:text>
				<xsl:text> 		 		}; &#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- codeList Template -->
	<!-- measure Template -->
	<xsl:template match="measure">
		<xsl:comment> ================== measure ==================== </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;public class </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>{ &#xA;</xsl:text>
				<xsl:text> 		 		public </xsl:text>
				<xsl:value-of select="type"/>
				<xsl:text> value;&#xA;</xsl:text>
				<xsl:text> 		 		public String unit;&#xA;</xsl:text>
				<xsl:text> 		 		}; &#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- measure Template -->
	<!-- class -->
	<xsl:template match="class">
		<xsl:call-template name="Separator"/>
		<xsl:comment> =================== class =================== </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:if test="isAbstract">
				<xsl:attribute name="abstract">true</xsl:attribute>
			</xsl:if>
			<xsl:if test="final">
				<xsl:attribute name="final">true</xsl:attribute>
			</xsl:if>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;public interface </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:if test="superclass">
					<xsl:text> extends </xsl:text>
					<xsl:for-each select="superclass">
						<xsl:text>&#xA;					</xsl:text>
						<xsl:if test="packageNameSpace">
							<xsl:value-of select="packageNameSpace"/>
							<xsl:text>.</xsl:text>
						</xsl:if>
						<xsl:value-of select="name"/>
						<xsl:if test="position()!=last()">, </xsl:if>
					</xsl:for-each>
				</xsl:if>
				<xsl:text>&#xA;&#x9;&#x9;{&#xA;</xsl:text>
				<xsl:call-template name="AttributesForComplexType"/>
				<xsl:call-template name="Method"/>
				<xsl:call-template name="Constructor"/>
				<xsl:text>&#x9;&#x9;};&#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- dataType -->
	<xsl:template match="dataType">
		<xsl:call-template name="Separator"/>
		<xsl:comment> =================== dataType ================ </xsl:comment>
		<xsl:element name="classDefinition">
			<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			<xsl:if test="isAbstract">
				<xsl:attribute name="abstract">true</xsl:attribute>
			</xsl:if>
			<xsl:if test="final">
				<xsl:attribute name="final">true</xsl:attribute>
			</xsl:if>
			<xsl:call-template name="Doc"/>
			<xsl:element name="Java">
				<xsl:for-each select="packageNameSpace">
					<xsl:text>&#xA;package </xsl:text>
					<xsl:value-of select="."/>
					<xsl:text>;</xsl:text>
				</xsl:for-each>
				<xsl:text>&#xA;public interface </xsl:text>
				<xsl:value-of select="name"/>
				<xsl:if test="superclass">
					<xsl:text> extends </xsl:text>
					<xsl:for-each select="superclass">
						<xsl:value-of select="."/>
						<xsl:if test="position()!=last()">, </xsl:if>
					</xsl:for-each>
				</xsl:if>
				<xsl:text>&#x9;&#x9;{&#xA;</xsl:text>
				<xsl:call-template name="AttributesForComplexType"/>
				<xsl:call-template name="Method"/>
				<xsl:call-template name="Constructor"/>
				<xsl:text>&#x9;&#x9;};&#xA;</xsl:text>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	<!-- AttributesForComplexType -->
	<xsl:template name="AttributesForComplexType">
		<xsl:for-each select="attribute">
			<xsl:call-template name="GetSetAttributeDefinition"/>
		</xsl:for-each>
	</xsl:template>
	<!-- AttributesForEnumType -->
	<xsl:template name="AttributesForEnumType">
		<xsl:for-each select="attribute">
			<xsl:call-template name="EnumAttributeDefinition"/>
		</xsl:for-each>
	</xsl:template>
	<!-- GetSetAttributeDefinition (change by James MacGill, PSU) -->
	<xsl:variable name="upperCase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'"/>
	<xsl:variable name="lowerCase" select="'abcdefghijklmnopqrstuvwxyz'"/>
	<xsl:template name="GetSetAttributeDefinition">
		<xsl:text>&#x9;&#x9;</xsl:text>
		<xsl:if test="not(readonly)">
			<xsl:text>void</xsl:text>
			<xsl:text>&#x20;</xsl:text>
			<xsl:text>set</xsl:text>
			<xsl:value-of select="translate((substring(name, 1,1) ), $lowerCase, $upperCase)"/>
			<xsl:value-of select="substring(name, 2) "/>
			<xsl:text>(</xsl:text>
			<xsl:for-each select="packageNameSpace">
				<xsl:value-of select="."/>
				<xsl:text>.</xsl:text>
			</xsl:for-each>
			<xsl:value-of select="type"/>
			<xsl:text>&#x20;</xsl:text>
			<xsl:value-of select="name"/>
			<xsl:text>)</xsl:text>
			<xsl:text>;&#xA;</xsl:text>
		</xsl:if>
		<xsl:text>&#x9;&#x9;</xsl:text>
		<!-- <xsl:text>pubic </xsl:text> -->
		<xsl:for-each select="packageNameSpace">
			<xsl:value-of select="."/>
			<xsl:text>.</xsl:text>
		</xsl:for-each>
		<xsl:value-of select="type"/>
		<xsl:text>&#x20;</xsl:text>
		<xsl:text>get</xsl:text>
		<xsl:value-of select="translate((substring(name, 1,1) ), $lowerCase, $upperCase)"/>
		<xsl:value-of select="substring(name, 2) "/>
		<xsl:text>()</xsl:text>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>
	<!-- <xsl:template name="AttributeDefinition">
		<xsl:text>&#x9;&#x9;</xsl:text>
		<xsl:if test="readonly">
			<xsl:text>readonly </xsl:text>
		</xsl:if>
		<xsl:text>pubic </xsl:text>
		<xsl:value-of select="type"/>
		<xsl:text>&#x20;</xsl:text>
		<xsl:value-of select="name"/>
		<xsl:if test="derived">
			<xsl:text>()</xsl:text>
		</xsl:if>
		<xsl:text>;&#xA;</xsl:text>
	</xsl:template>
	-->
	<!-- EnumAttributeDefinition -->
	<xsl:template name="EnumAttributeDefinition">
		<xsl:text>&#x20;				&quot;</xsl:text>
		<xsl:value-of select="name"/>
		<xsl:text>&quot;</xsl:text>
		<xsl:if test="position()!=last()">, </xsl:if>
		<xsl:text>&#xA;				</xsl:text>
	</xsl:template>
	<!-- EnumRefAttributeDefinition-->
	<xsl:template name="EnumRefAttributeDefinition">
		<xsl:choose>
			<xsl:when test="name = 'indirect'"/>
			<xsl:otherwise>
				<xsl:text>&#x20;e_</xsl:text>
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
				<xsl:text>&#x20; </xsl:text>
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
				<xsl:text>&#x9;&#x9;case e_</xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>:&#x20;</xsl:text>
				<xsl:value-of select="type"/>
				<xsl:text>&#x20;</xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>;&#xA;</xsl:text>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<!--  UnionRefAttributeDefinition  -->
	<xsl:template name="UnionRefAttributeDefinition">
		<xsl:choose>
			<xsl:when test="name = 'indirect'"/>
			<xsl:otherwise>
				<xsl:text>&#x9;&#x9;case e_</xsl:text>
				<xsl:value-of select="name"/>
				<xsl:text>_ptr:&#x20; </xsl:text>
				<xsl:value-of select="type"/>
				<xsl:text>_ptr&#x20;</xsl:text>
				<xsl:value-of select="type"/>
				<xsl:text>_ptr;&#xA;</xsl:text>
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
	<!-- Method Template -->
	<xsl:template name="Method">
		<xsl:for-each select="method">
		      <xsl:if test="substring(methodName,1,4) != 'init'">
			<xsl:text>&#x9;&#x9;</xsl:text>
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
			<xsl:text>&#x20; </xsl:text>
			<xsl:value-of select="methodName"/>
			<xsl:text> (</xsl:text>
			<xsl:call-template name="Parameter"/>
			<xsl:text> );&#xA;</xsl:text>
			</xsl:if>
		</xsl:for-each>
	</xsl:template>
	<!-- Method Template -->
	<!-- constructor Template -->
	<xsl:template name="Constructor">
	  <!-- 
		<xsl:for-each select="constructor">
			<xsl:text>&#x9;&#x9;</xsl:text>
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
			<xsl:text>&#x20; </xsl:text>
			<xsl:value-of select="methodName"/>
			<xsl:text> (</xsl:text>
			<xsl:call-template name="Parameter"/>
			<xsl:text> );&#xA;</xsl:text>
		</xsl:for-each>
	   -->
	</xsl:template>
	<!-- constructor Template -->
	<!-- Parameter Template -->
	<xsl:template name="Parameter">
		<xsl:for-each select="parameter">
			<xsl:text>&#xA; 					</xsl:text>
			<xsl:if test="packageNameSpace">
				<xsl:value-of select="packageNameSpace"/>
				<xsl:text>.</xsl:text>
			</xsl:if>
			<xsl:value-of select="parameterType"/>
			<xsl:text>&#x20; </xsl:text>
			<xsl:value-of select="parameterName"/>
			<xsl:if test="position()!=last()">, </xsl:if>
		</xsl:for-each>
	</xsl:template>
	<!-- Parameter Template -->
	<xsl:template name="Separator">
		<xsl:text disable-output-escaping="yes">
       &lt;!-- =========================================== --&gt;&#xA;</xsl:text>
	</xsl:template>
</xsl:transform>
