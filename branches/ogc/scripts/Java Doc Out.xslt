<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:java="http://www.opengis.net/Java">
	<xsl:output method="text" encoding="UTF-8"/>
	<xsl:preserve-space elements="java:IDL java:classDefinition java:Declarations"/>
	<xsl:strip-space elements="java:comments"/>
	<xsl:template match="java:Declarations">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="java:Java">
		<xsl:text>	&#xA; </xsl:text>
		<xsl:apply-templates/>
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>
	<xsl:template match="java:classDefinition">
		<xsl:text>&#xA;//  Class:  </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>&#xA;</xsl:text>
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="java:comment">
		<xsl:text>	&#xA; </xsl:text>
		<xsl:apply-templates/>
		<xsl:text> &#xA;</xsl:text>
	</xsl:template>
	<xsl:template match="java:date">
		<xsl:text>	&#xA; </xsl:text>
		<xsl:apply-templates/>
		<xsl:text> &#xA;</xsl:text>
	</xsl:template>
</xsl:stylesheet>
