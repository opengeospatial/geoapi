<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" exclude-result-prefixes="#default">
	<xsl:output method="xml" indent="yes"/>
	<xsl:key name="classifier" match="//Foundation.Core.Class | //Foundation.Core.Interface | //Foundation.Core.DataType" use="@xmi.id"/>
	<xsl:key name="generalization" match="//Foundation.Core.Generalization" use="@xmi.id"/>
	<xsl:key name="abstraction" match="//Foundation.Core.Abstraction" use="@xmi.id"/>
	<xsl:key name="multiplicity" match="//Foundation.Data_Types.Multiplicity" use="@xmi.id"/>
	<xsl:key name="stereotype" match="//Foundation.Extension_Mechanisms.Stereotype" use="@xmi.id"/>
	<!-- Document Root -->
	<xsl:template match="/">
		
		<classList  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="newUMLSchema.xsd">
			<!-- please please please only be root-level packages -->
			<xsl:apply-templates select="//Model_Management.Package[@xmi.id]">
				<xsl:sort select="Foundation.Core.ModelElement.name"/>
			</xsl:apply-templates>			
		</classList>
	</xsl:template>
	
	<xsl:template match="Model_Management.Package">
		<package>
			<xsl:variable name="javaprefix" select="Foundation.Core.ModelElement.taggedValue/Foundation.Extension_Mechanisms.TaggedValue[Foundation.Core.ModelElement.name='namespace']/Foundation.Extension_Mechanisms.TaggedValue.dataValue"/>
		
			<packageName>
				<xsl:value-of select="Foundation.Core.ModelElement.name"/>
			</packageName>
			
			<!-- doesn't work in MD unisys -->
			
			<javaprefix>
				<xsl:value-of select="$javaprefix"/>
			</javaprefix>
	
			<!-- Interfaces -->
			<xsl:apply-templates select="./Foundation.Core.Namespace.ownedElement/Foundation.Core.Interface[@xmi.id]">
				<xsl:with-param name="javaprefix" select="$javaprefix"/>
				<xsl:sort select="Foundation.Core.ModelElement.name"/>
			</xsl:apply-templates>
			
			<!-- Classes -->
			<xsl:apply-templates select="./Foundation.Core.Namespace.ownedElement/Foundation.Core.Class[@xmi.id]">
				<xsl:with-param name="javaprefix" select="$javaprefix"/>
				<xsl:sort select="Foundation.Core.ModelElement.name"/>
			</xsl:apply-templates>
			
			<!-- DataTypes -->
			<xsl:apply-templates select="./Foundation.Core.Namespace.ownedElement/Foundation.Core.DataType[@xmi.id]">
				<xsl:with-param name="javaprefix" select="$javaprefix"/>
				<xsl:sort select="Foundation.Core.ModelElement.name"/>
			</xsl:apply-templates>
		</package>
	</xsl:template>
	
	<xsl:template name="class">
		<xsl:param name="isAbstract"/>
		<xsl:param name="javaprefix"/>
		<xsl:variable name="element_name" select="translate(Foundation.Core.ModelElement.name, '&lt;&gt;', '__')"/>
		<xsl:variable name="xmi_id" select="@xmi.id"/>

		<name>
			<xsl:value-of select="$element_name"/>
		</name>
		
		<xsl:call-template name="comment"/>
		
		<xsl:apply-templates select="Foundation.Core.ModelElement.visibility"/>
		
		<!-- Test Abstract -->
		<xsl:if test="Foundation.Core.GeneralizableElement.isAbstract/@xmi.value='true' or $isAbstract='true'">
			<isAbstract/>
		</xsl:if>
		
		<!-- packagenamespace -->
		<packageNameSpace>
			<xsl:value-of select="$javaprefix"/>
		</packageNameSpace>
		
		<xsl:call-template name="supertypes"/>
		<xsl:call-template name="specifications"/>
		<xsl:call-template name="attributes"/>
		<xsl:call-template name="associations">
		      <xsl:with-param name="source" select="$xmi_id"/>
     	      </xsl:call-template>
		<xsl:call-template name="operations"/>
	</xsl:template>
	
	<!-- Interface -->
	<xsl:template match="Foundation.Core.Interface">
		<xsl:param name="javaprefix"/>
		<interface>
			<xsl:call-template name="class">
				<xsl:with-param name="isAbstract">false</xsl:with-param>
				<xsl:with-param name="javaprefix" select="$javaprefix"/>
			</xsl:call-template>
		</interface>
	</xsl:template>
	
	<!-- DataType -->
	<xsl:template match="Foundation.Core.DataType">
		<xsl:param name="javaprefix"/>
		<xsl:variable name="name" select="Foundation.Core.ModelElement.name"/>
		<xsl:variable name="lower_name" select="translate($name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')"/>
		<!-- check if the first character is lowercase and ignore -->
		<xsl:if test="substring($name,1,1)!=substring($lower_name,1,1)">
			<dataType>
				<xsl:call-template name="class">
					<xsl:with-param name="isAbstract">false</xsl:with-param>
					<xsl:with-param name="javaprefix" select="$javaprefix"/>
				</xsl:call-template>
			</dataType>
		</xsl:if>
	</xsl:template>
	
	<!-- Class -->
	<xsl:template match="Foundation.Core.Class">
		<xsl:param name="javaprefix"/>
		<xsl:variable name="xmi_id" select="@xmi.id"/>
		<xsl:variable name="stereotype_target" select="Foundation.Core.ModelElement.stereotype/Foundation.Extension_Mechanisms.Stereotype/@xmi.idref"/>
		<xsl:variable name="stereotype_element" select="key('stereotype', $stereotype_target)"/>
		<xsl:variable name="stereotype_name" select="translate($stereotype_element/Foundation.Core.ModelElement.name, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')"/>
		<xsl:variable name="classtype">
			<xsl:choose>
				<xsl:when test="$stereotype_name='datatype'">dataType</xsl:when>
				<xsl:when test="$stereotype_name='enumeration'">enumeration</xsl:when>
				<xsl:when test="$stereotype_name='union'">union</xsl:when>
				<xsl:when test="$stereotype_name='codelist'">codeList</xsl:when>
				<xsl:when test="$stereotype_name='interface'">interface</xsl:when>
				<xsl:otherwise>class</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
	
		<xsl:variable name="isAbstract">
			<xsl:choose>
				<xsl:when test="$stereotype_name='abstract'">true</xsl:when>
				<xsl:otherwise>false</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
					
		<xsl:element name="{$classtype}">
			<xsl:call-template name="class">
				<xsl:with-param name="isAbstract" select="$isAbstract"/>
				<xsl:with-param name="javaprefix" select="$javaprefix"/>
			</xsl:call-template>			
		</xsl:element>		
	</xsl:template>
	
	
	
	<!-- Specifications (interface or class) -->
	<xsl:template name="specifications">
		<!-- Abstractions identify specifications -->
		<xsl:variable name="specifications" select="Foundation.Core.ModelElement.clientDependency/Foundation.Core.Abstraction"/>
		<xsl:if test="count($specifications) > 0">
			<xsl:for-each select="$specifications">
				<!-- get the supplier in the abstraction -->
				<xsl:variable name="abstraction" select="key('abstraction', ./@xmi.idref)"/>
				<xsl:variable name="target" select="$abstraction/Foundation.Core.Dependency.supplier/*/@xmi.idref"/>
				<specification>
					<name>
						<xsl:call-template name="classname">
							<xsl:with-param name="target" select="$target"/>
						</xsl:call-template>
					</name>
				</specification>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	
	<!-- Supertypes (inheritance) -->
	<xsl:template name="supertypes">
		<!-- Generalizations identify supertypes -->
		<xsl:variable name="generalizations" select="Foundation.Core.GeneralizableElement.generalization/Foundation.Core.Generalization"/>
		<xsl:if test="count($generalizations) > 0">
			<xsl:for-each select="$generalizations">
				<xsl:variable name="generalization" select="key('generalization', ./@xmi.idref)"/>
				<xsl:variable name="target" select="$generalization/Foundation.Core.Generalization.parent/*/@xmi.idref"/>
				<superclass>
					<name>
						<xsl:call-template name="classname">
							<xsl:with-param name="target" select="$target"/>
						</xsl:call-template>
					</name>
				</superclass>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>
	
	<!-- Visibility -->
	<xsl:template match="Foundation.Core.ModelElement.visibility">
		<visibility>
			<xsl:value-of select="@xmi.value"/>
		</visibility>
	</xsl:template>
	
	<!-- Multiplicity -->
	<xsl:template match="Foundation.Data_Types.Multiplicity">
		<xsl:variable name="range" select="Foundation.Data_Types.Multiplicity.range/Foundation.Data_Types.MultiplicityRange"/>
		<xsl:variable name="min" select="$range/Foundation.Data_Types.MultiplicityRange.lower"/>
		<xsl:variable name="max" select="$range/Foundation.Data_Types.MultiplicityRange.upper"/>
		<cardinality>
			<xsl:if test="$min>=0">
				<min>
					<xsl:value-of select="$min"/>
				</min>
			</xsl:if>
			<xsl:if test="$max>=0">
				<max>
					<xsl:value-of select="$max"/>
				</max>
			</xsl:if>
		</cardinality>
	</xsl:template>
	
	<!-- Attributes -->
	<xsl:template name="attributes">
		<xsl:apply-templates select="Foundation.Core.Classifier.feature/Foundation.Core.Attribute"/>
	</xsl:template>
	
	<!--Attribute -->
	<xsl:template match="Foundation.Core.Attribute">
		<xsl:variable name="target" select="Foundation.Core.StructuralFeature.type/*/@xmi.idref"/>
		<attribute>
			<name>
				<xsl:value-of select="Foundation.Core.ModelElement.name"/>
			</name>
			
			<xsl:call-template name="comment"/>
			
			<type>
				<xsl:call-template name="classname">
					<xsl:with-param name="target" select="$target"/>
				</xsl:call-template>
			</type>
			<xsl:apply-templates select="Foundation.Core.ModelElement.visibility"/>
			<xsl:apply-templates select="Foundation.Core.StructuralFeature.multiplicity/Foundation.Data_Types.Multiplicity"/>
		</attribute>
	</xsl:template>
	
	<!-- Operations -->
	<xsl:template name="operations">
		<xsl:apply-templates select="Foundation.Core.Classifier.feature/Foundation.Core.Operation"/>
	</xsl:template>
	
	<!-- Operation -->
	<xsl:template match="Foundation.Core.Operation">
		<!-- In rose-xmi the return types aren't specified correctly (the kind="in" rather than -->
		<!-- "return"). They can be detected by having a blank name, however
		<xsl:variable name="parameters" select="Foundation.Core.BehavioralFeature.parameter/
			Foundation.Core.Parameter
      			[Foundation.Core.Parameter.kind/@xmi.value!='return'
      			and string-length(Foundation.Core.ModelElement.name) > 0]"/>
		<xsl:variable name="return" select="Foundation.Core.BehavioralFeature.parameter/
      			Foundation.Core.Parameter
     			[Foundation.Core.Parameter.kind/@xmi.value='return'
      			or string-length(Foundation.Core.ModelElement.name)=0]"/> -->
      		<xsl:variable name="parameters" select="Foundation.Core.BehavioralFeature.parameter/
			Foundation.Core.Parameter[Foundation.Core.Parameter.kind/@xmi.value!='return']"/>
		<xsl:variable name="return" select="Foundation.Core.BehavioralFeature.parameter/
      			Foundation.Core.Parameter[Foundation.Core.Parameter.kind/@xmi.value='return']"/>
		<xsl:variable name="target" select="$return/Foundation.Core.Parameter.type/*/@xmi.idref"/>
		<method>
			<methodName>
				<xsl:value-of select="Foundation.Core.ModelElement.name"/>
			</methodName>
			
			<xsl:call-template name="comment"/>
			
			<xsl:apply-templates select="Foundation.Core.ModelElement.visibility"/>
			<xsl:variable name="parameter-count" select="count($parameters)"/>
			<xsl:if test="not(normalize-space($parameter-count)='0')">
				<xsl:apply-templates select="$parameters"/>
			</xsl:if>
			<returnType>
				<type>
					<xsl:choose>
						<xsl:when test="string-length($target) > 0">
							<xsl:call-template name="classname">
								<xsl:with-param name="target" select="$target"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:text>void</xsl:text>
						</xsl:otherwise>
					</xsl:choose>
				</type>
			</returnType>
		</method>
	</xsl:template>
	
	<!-- Parameter -->
	<xsl:template match="Foundation.Core.Parameter">
		<xsl:variable name="target" select="Foundation.Core.Parameter.type/*/@xmi.idref"/>
		<parameter>
			<parameterName>
				<xsl:value-of select="Foundation.Core.ModelElement.name"/>
			</parameterName>
			<parameterType>
				<xsl:call-template name="classname">
					<xsl:with-param name="target" select="$target"/>
				</xsl:call-template>
			</parameterType>
			<xsl:if test="XMI.extension/typeModifier/@xmi.value='[]'">
				<isArray/>
			</xsl:if>
		</parameter>
	</xsl:template>
	
	<!-- Associations -->
	<xsl:template name="associations">
		<xsl:param name="source"/>
    
    		<xsl:variable name="association_ends" select="//Foundation.Core.AssociationEnd[Foundation.Core.AssociationEnd.type/*/@xmi.idref=$source]" />

		<xsl:if test="count($association_ends) > 0">
			<!-- Find the other side -->
		      <xsl:for-each select="$association_ends">
				<xsl:for-each select="preceding-sibling::Foundation.Core.AssociationEnd | following-sibling::Foundation.Core.AssociationEnd">
					<xsl:call-template name="association_end" />
      			  	</xsl:for-each>
			</xsl:for-each>
 		</xsl:if>  
	</xsl:template>
	
	<!-- Association End -->
	<xsl:template name="association_end">
		<xsl:variable name="target">
			<xsl:choose>
				<xsl:when test="Foundation.Core.AssociationEnd.type/Foundation.Core.Class/@xmi.idref">
					<xsl:value-of select="Foundation.Core.AssociationEnd.type/Foundation.Core.Class/@xmi.idref"/>
				</xsl:when>
				<xsl:when test="Foundation.Core.AssociationEnd.type/Foundation.Core.Interface/@xmi.idref">
					<xsl:value-of select="Foundation.Core.AssociationEnd.type/Foundation.Core.Interface/@xmi.idref"/>
				</xsl:when>
				<xsl:when test="Foundation.Core.AssociationEnd.type/Foundation.Core.DataType/@xmi.idref">
					<xsl:value-of select="Foundation.Core.AssociationEnd.type/Foundation.Core.DataType/@xmi.idref"/>
				</xsl:when>
			</xsl:choose>
		</xsl:variable>
		
		<xsl:if test="Foundation.Core.ModelElement.name">
		<attribute>
				<name>
					<xsl:value-of select="Foundation.Core.ModelElement.name"/>
				</name>
				<type>
					<xsl:call-template name="classname">
						<xsl:with-param name="target" select="$target"/>
					</xsl:call-template>
				</type>
				<xsl:apply-templates select="Foundation.Core.ModelElement.visibility"/>
				<xsl:apply-templates select="Foundation.Core.AssociationEnd.multiplicity/Foundation.Data_Types.Multiplicity"/>
			</attribute>
		</xsl:if>
	</xsl:template>
	
	<!-- Classname -->
	<xsl:template name="classname">
		<xsl:param name="target"/>
		<xsl:variable name="classifier" select="key('classifier', $target)"/>
		<xsl:value-of select="$classifier/Foundation.Core.ModelElement.name"/>
	</xsl:template>
	
	<!-- Comment -->
	<xsl:template name="comment">
		<xsl:if test="Foundation.Core.ModelElement.comment">
			<comment>
				<xsl:value-of select="Foundation.Core.ModelElement.comment/Foundation.Core.Comment[@xmi.id]/Foundation.Core.ModelElement.name"/>
			</comment>
		</xsl:if>
	</xsl:template>
	
</xsl:stylesheet>
