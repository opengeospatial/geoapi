<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:saxon="http://icl.com/saxon" xmlns:java="http://www.opengis.net/Java">
	<xsl:output method="text" encoding="UTF-8"/>
	<!-- <xsl:preserve-space elements="java:Java java:classDefinition"/> -->
	<!-- <xsl:strip-space elements="java:comments"/> -->
	<xsl:param name="dir">f:\projects\geometry\direct\packaged</xsl:param>
	<xsl:variable name="filesep" select="system-property('file.separator')"/>
	<xsl:variable name="period">.</xsl:variable>
	<xsl:template match="java:Declarations">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="java:javaPrefix"/>
	<xsl:template match="java:comment"/>
	<xsl:template match="java:date"/>
	<xsl:template match="java:predefine"/>
	<xsl:template match="java:comment"/>
	<xsl:template match="java:Java">
		<xsl:apply-templates/>
	</xsl:template>
	<xsl:template match="java:classDefinition"/>
	<xsl:template match="java:packageName"/>
	<xsl:template match="java:package">
		<xsl:variable name="javaroot">
			<xsl:value-of select="./java:javaPrefix"/>
		</xsl:variable>
		<xsl:variable name="javadir">
			<xsl:value-of select="translate($javaroot, $period, $filesep)"/>
		</xsl:variable>
		<xsl:text>&#xA;//  package:  </xsl:text>
		<xsl:value-of select="java:packageName"/>
		<xsl:text>&#xA;//  javaroot:  </xsl:text>
		<xsl:value-of select="$javaroot"/>
		<xsl:text>&#xA;//  javadir:  </xsl:text>
		<xsl:value-of select="$javadir"/>
		<xsl:apply-templates/>
		<xsl:for-each select="java:classDefinition">
			<xsl:call-template name="classDefinition">
				<xsl:with-param name="javaroot">
					<xsl:value-of select="$javaroot"/>
				</xsl:with-param>
				<xsl:with-param name="javadir">
					<xsl:value-of select="$javadir"/>
				</xsl:with-param>
			</xsl:call-template>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="classDefinition">
		<xsl:param name="javaroot"/>
		<xsl:param name="javadir"/>
		<xsl:variable name="filename">
			<xsl:text>.</xsl:text>
			<xsl:value-of select="$filesep"/>
			<xsl:value-of select="$javadir"/>
			<xsl:value-of select="$filesep"/>
			<xsl:value-of select="@name"/>
			<xsl:text>.java</xsl:text>
		</xsl:variable>
		<xsl:text>&#xA;//  Class:  </xsl:text>
		<xsl:value-of select="@name"/>
		<xsl:text>&#xA;//  fllename:  </xsl:text>
		<xsl:value-of select="$filename"/>
		<!-- new file code -->
		<xsl:call-template name="newFile">
			<xsl:with-param name="filename">
				<xsl:value-of select="$dir"/>
				<xsl:value-of select="$filesep"/>
				<xsl:value-of select="$filename"/>
			</xsl:with-param>
			<xsl:with-param name="content">
				<xsl:text>&#xA;//  Class:  </xsl:text>
				<xsl:value-of select="@name"/>
				<xsl:apply-templates/>
			</xsl:with-param>
		</xsl:call-template>
		<!-- end of new file code -->
	</xsl:template>
	<!-- new file template -->
	<xsl:template name="newFile">
		<xsl:param name="filename"/>
		<xsl:param name="content"/>
		<xsl:document href="{$filename}" method="text" encoding="UTF-8">
			<xsl:copy-of select="$content"/>
		</xsl:document>
	</xsl:template>
</xsl:stylesheet>
