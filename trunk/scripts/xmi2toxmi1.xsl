<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:UML="org.omg.xmi.namespace.UML">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<xsl:apply-templates select="XMI"/>
	</xsl:template>
	
	<!-- XMI -->
	<xsl:template match="XMI">
		<xsl:element name="XMI">
			<xsl:attribute name="xmi.version">1.0</xsl:attribute>
			<!-- XMI.header -->
			<!-- XMI.content -->
			<xsl:apply-templates select="XMI.content"/>
		</xsl:element>
	</xsl:template>
	
	<!-- XMI.Content -->
	<xsl:template match="XMI.content">
		<xsl:element name="XMI.content">
			<!-- UML:Expression -->
			<xsl:apply-templates select="UML:Model"/>
			<!-- UML:Comment -->
			<!-- UML:TagDefinition -->
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Model -->
	<xsl:template match="UML:Model">
		<xsl:element name="Model_Management.Model">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="GeneralizableElement.isRoot"/>
			<xsl:call-template name="GeneralizableElement.isLeaf"/>
			<xsl:call-template name="GeneralizableElement.isAbstract"/>
			<xsl:apply-templates select="UML:Namespace.ownedElement"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Package -->
	<xsl:template match="UML:Package">
		<xsl:element name="Model_Management.Package">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="GeneralizableElement.isRoot"/>
			<xsl:call-template name="GeneralizableElement.isLeaf"/>
			<xsl:call-template name="GeneralizableElement.isAbstract"/>
			<xsl:apply-templates select="UML:Namespace.ownedElement"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Class -->
	<xsl:template match="UML:Class">
		<xsl:element name="Foundation.Core.Class">
			<!-- check if a reference -->
			<xsl:choose>
				<xsl:when test="@xmi.idref">
					<xsl:call-template name="xmi.idref"/>
				</xsl:when>			
				<xsl:otherwise>
					<xsl:call-template name="xmi.id"/>
					<xsl:call-template name="ModelElement.name"/>
					<xsl:call-template name="ModelElement.visibility"/>
					<xsl:call-template name="GeneralizableElement.isRoot"/>
					<xsl:call-template name="GeneralizableElement.isLeaf"/>
					<xsl:call-template name="GeneralizableElement.isAbstract"/>
					<xsl:call-template name="Class.isActive"/>
					<xsl:apply-templates select="UML:ModelElement.stereotype"/>
					<xsl:apply-templates select="UML:GeneralizableElement.generalization"/>
					<!-- TODO specialization? -->
					<xsl:apply-templates select="UML:Classifier.feature"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Namespace.ownedElement -->
	<xsl:template match="UML:Namespace.ownedElement">
		<xsl:element name="Foundation.Core.Namespace.ownedElement">
			<xsl:apply-templates select="UML:Package"/>
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Association"/>
			<xsl:apply-templates select="UML:Interface"/>
			<xsl:apply-templates select="UML:Generalization"/>
			<xsl:apply-templates select="UML:Constraint"/>
			<xsl:apply-templates select="UML:Stereotype"/>
			<xsl:apply-templates select="UML:Abstraction"/>
			<xsl:apply-templates select="UML:DataType"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Constraint -->
	<xsl:template match="UML:Constraint">
		<xsl:element name="Foundation.Core.Constraint">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:apply-templates select="UML:Constraint.body"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Constraint.body -->
	<xsl:template match="UML:Constraint.body">
		<xsl:element name="Foundation.Core.Constraint.body">
			<xsl:apply-templates select="UML:BooleanExpression"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:BooleanExpression -->
	<xsl:template match="UML:BooleanExpression">
		<xsl:element name="Foundation.Data_Types.BooleanExpression">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="Expression.body"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Abstraction -->
	<xsl:template match="UML:Abstraction">
		<xsl:element name="Foundation.Core.Abstraction">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:apply-templates select="UML:ModelElement.stereotype"/>
			<xsl:apply-templates select="UML:Dependency.client"/>
			<xsl:apply-templates select="UML:Dependency.supplier"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Dependency.client -->
	<xsl:template match="UML:Dependency.client">
		<xsl:element name="Foundation.Core.Dependency.client">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Dependency.supplier -->
	<xsl:template match="UML:Dependency.supplier">
		<xsl:element name="Foundation.Core.Dependency.supplier">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Interface -->
	<xsl:template match="UML:Interface">
		<xsl:element name="Foundation.Core.Interface">
			<!-- check if a reference -->
			<xsl:choose>
				<xsl:when test="@xmi.idref">
					<xsl:call-template name="xmi.idref"/>
				</xsl:when>			
				<xsl:otherwise>
					<xsl:call-template name="xmi.id"/>
					<xsl:call-template name="ModelElement.name"/>
					<xsl:call-template name="ModelElement.visibility"/>
					<xsl:call-template name="GeneralizableElement.isRoot"/>
					<xsl:call-template name="GeneralizableElement.isLeaf"/>
					<xsl:call-template name="GeneralizableElement.isAbstract"/>
					<!-- TODO stereotype? -->
					<xsl:apply-templates select="UML:GeneralizableElement.generalization"/>
					<!-- TODO specialization? -->
					<xsl:apply-templates select="UML:Classifier.feature"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Association -->
	<xsl:template match="UML:Association">
		<xsl:element name="Foundation.Core.Association">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="GeneralizableElement.isRoot"/>
			<xsl:call-template name="GeneralizableElement.isLeaf"/>
			<xsl:call-template name="GeneralizableElement.isAbstract"/>
			<xsl:apply-templates select="UML:Association.connection"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Association.connection -->
	<xsl:template match="UML:Association.connection">
		<xsl:element name="Foundation.Core.Association.connection">
			<xsl:apply-templates select="UML:AssociationEnd"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:AssociationEnd -->
	<xsl:template match="UML:AssociationEnd">
		<xsl:element name="Foundation.Core.AssociationEnd">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="ModelElement.visibility"/>
			<xsl:call-template name="AssociationEnd.isNavigable"/>
			<xsl:call-template name="AssociationEnd.ordering"/>
			<xsl:call-template name="AssociationEnd.aggregation"/>
			<xsl:call-template name="AssociationEnd.targetScope"/>
			<xsl:apply-templates select="UML:AssociationEnd.multiplicity"/>
			<xsl:call-template name="AssociationEnd.changeability"/>
			<!-- TODO UML:Association.association -->
			<xsl:apply-templates select="UML:AssociationEnd.participant"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:AssociationEnd.participant -->
	<xsl:template match="UML:AssociationEnd.participant">
		<xsl:element name="Foundation.Core.AssociationEnd.type">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.multiplicity -->
	<xsl:template match="UML:AssociationEnd.multiplicity">
		<xsl:element name="Foundation.Core.AssociationEnd.multiplicity">
			<xsl:apply-templates select="UML:Multiplicity"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:GeneralizableElement.generalization -->
	<xsl:template match="UML:GeneralizableElement.generalization">
		<xsl:element name="Foundation.Core.GeneralizableElement.generalization">
			<xsl:apply-templates select="UML:Generalization"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Generalization -->
	<xsl:template match="UML:Generalization">
		<xsl:element name="Foundation.Core.Generalization">
			<xsl:choose>
				<xsl:when test="@xmi.idref">
					<xsl:call-template name="xmi.idref"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="xmi.id"/>
					<xsl:apply-templates select="UML:Generalization.child"/>
					<xsl:apply-templates select="UML:Generalization.parent"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Generalization.child -->
	<xsl:template match="UML:Generalization.child">
		<xsl:element name="Foundation.Core.Generalization.child">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Generalization.parent -->
	<xsl:template match="UML:Generalization.parent">
		<xsl:element name="Foundation.Core.Generalization.parent">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:ModelElement.stereotype -->
	<xsl:template match="UML:ModelElement.stereotype">
		<xsl:element name="Foundation.Core.ModelElement.stereotype">
			<xsl:apply-templates select="UML:Stereotype"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Stereotype -->
	<xsl:template match="UML:Stereotype">
		<xsl:element name="Foundation.Extension_Mechanisms.Stereotype">
			<xsl:choose>
				<xsl:when test="@xmi.idref">
					<xsl:call-template name="xmi.idref"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="xmi.id"/>
					<xsl:call-template name="ModelElement.name"/>
					<xsl:call-template name="GeneralizableElement.isRoot"/>
					<xsl:call-template name="GeneralizableElement.isLeaf"/>
					<xsl:call-template name="GeneralizableElement.isAbstract"/>
					<xsl:call-template name="Stereotype.baseClass"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Classifier.feature -->
	<xsl:template match="UML:Classifier.feature">
		<xsl:element name="Foundation.Core.Classifier.feature">
			<xsl:apply-templates select="UML:Attribute"/>
			<xsl:apply-templates select="UML:Operation"/>
			<!-- UML:Method -->
		</xsl:element>
	</xsl:template>
	
	<!-- UML:BehavioralFeature.parameter -->
	<xsl:template match="UML:BehavioralFeature.parameter">
		<xsl:element name="Foundation.Core.BehavioralFeature.parameter">
			<xsl:apply-templates select="UML:Parameter"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Parameter -->
	<xsl:template match="UML:Parameter">
		<xsl:element name="Foundation.Core.Parameter">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="Parameter.kind"/>
			<xsl:apply-templates select="UML:Parameter.type"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Parameter.type -->
	<xsl:template match="UML:Parameter.type">
		<xsl:element name="Foundation.Core.Parameter.type">
			<xsl:apply-templates select="UML:Class"/>
			<xsl:apply-templates select="UML:Interface"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Operation -->
	<xsl:template match="UML:Operation">
		<xsl:element name="Foundation.Core.Operation">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="ModelElement.visibility"/>
			<xsl:call-template name="Feature.ownerScope"/>
			<xsl:call-template name="BehavioralFeature.isQuery"/>
			<xsl:call-template name="Operation.concurrency"/>
			<xsl:call-template name="Operation.isRoot"/>
			<xsl:call-template name="Operation.isLeaf"/>
			<xsl:call-template name="Operation.isAbstract"/>
			<!-- TODO owner -->
			<xsl:apply-templates select="UML:BehavioralFeature.parameter"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Attribute -->
	<xsl:template match="UML:Attribute">
		<xsl:element name="Foundation.Core.Attribute">
			<xsl:call-template name="xmi.id"/>
			<xsl:call-template name="ModelElement.name"/>
			<xsl:call-template name="ModelElement.visibility"/>
			<xsl:call-template name="Feature.ownerScope"/>
			<xsl:apply-templates select="UML:StructuralFeature.multiplicity"/>
			<xsl:call-template name="StructuralFeature.changeability"/>
			<xsl:call-template name="StructuralFeature.targetScope"/>
			<!-- TODO Ordering? -->
			<xsl:apply-templates select="UML:Attribute.initialValue"/>
			<!-- TODO Owner - a reference to the current class -->
			<xsl:apply-templates select="UML:StructuralFeature.type"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Expression -->
	<xsl:template match="UML:Expression">
		<xsl:element name="Foundation.Data_Types.Expression">
			<xsl:call-template name="xmi.id"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Attribute.initialValue -->
	<xsl:template match="UML:Attribute.initialValue">
		<xsl:element name="Foundation.Core.Attribute.initialValue">
			<xsl:apply-templates select="UML:Expression"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:StructuralFeature.multiplicity -->
	<xsl:template match="UML:StructuralFeature.multiplicity">
		<xsl:element name="Foundation.Core.StructuralFeature.multiplicity">
			<!-- UML:Multiplicity -->
			<xsl:apply-templates select="UML:Multiplicity"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:StructuralFeature.type -->
	<xsl:template match="UML:StructuralFeature.type">
		<xsl:element name="Foundation.Core.StructuralFeature.type">
			<xsl:apply-templates select="UML:DataType"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:DataType -->
	<xsl:template match="UML:DataType">
		<!--TODO check if theres a non-idref version -->
		<xsl:element name="Foundation.Core.DataType">
			<xsl:choose>
				<xsl:when test="@xmi.idref">
					<xsl:call-template name="xmi.idref"/>
				</xsl:when>
				<xsl:otherwise>
					<xsl:call-template name="xmi.id"/>
					<xsl:call-template name="ModelElement.name"/>
					<xsl:call-template name="ModelElement.visibility"/>
					<xsl:call-template name="GeneralizableElement.isRoot"/>
					<xsl:call-template name="GeneralizableElement.isLeaf"/>
					<xsl:call-template name="GeneralizableElement.isAbstract"/>
					<xsl:apply-templates select="UML:GeneralizableElement.generalization"/>
					<!-- TODO specialization? -->
					<xsl:apply-templates select="UML:Classifier.feature"/>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Multiplicity -->
	<xsl:template match="UML:Multiplicity">
		<xsl:element name="Foundation.Data_Types.Multiplicity">
			<xsl:call-template name="xmi.id"/>
			<xsl:apply-templates select="UML:Multiplicity.range"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:Multiplicity.range -->
	<xsl:template match="UML:Multiplicity.range">
		<xsl:element name="Foundation.Data_Types.Multiplicity.range">
			<xsl:apply-templates select="UML:MultiplicityRange"/>
		</xsl:element>
	</xsl:template>
	
	<!-- UML:MultiplicityRange -->
	<xsl:template match="UML:MultiplicityRange">
		<xsl:element name="Foundation.Data_Types.MultiplicityRange">
			<xsl:call-template name="xmi.id"/>
			<!-- lower -->
			<xsl:element name="Foundation.Data_Types.MultiplicityRange.lower">
				<xsl:value-of select="@lower"/>
			</xsl:element>
			<!-- upper -->
			<xsl:element name="Foundation.Data_Types.MultiplicityRange.upper">
				<xsl:value-of select="@upper"/>
			</xsl:element>
		</xsl:element>
	</xsl:template>
	
	<!-- changeability -->
	<xsl:template name="StructuralFeature.changeability">
		<xsl:element name="Foundation.Core.StructuralFeature.changeability">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@changeability"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- targetScope -->
	<xsl:template name="StructuralFeature.targetScope">
		<xsl:element name="Foundation.Core.StructuralFeature.targetScope">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@targetScope"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- ownerScope -->
	<xsl:template name="Feature.ownerScope">
		<xsl:element name="Foundation.Core.Feature.ownerScope">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@ownerScope"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- isRoot -->
	<xsl:template name="GeneralizableElement.isRoot">
		<xsl:element name="Foundation.Core.GeneralizableElement.isRoot">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isRoot"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- isLeaf -->
	<xsl:template name="GeneralizableElement.isLeaf">
		<xsl:element name="Foundation.Core.GeneralizableElement.isLeaf">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isLeaf"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- isAbstract -->
	<xsl:template name="GeneralizableElement.isAbstract">
		<xsl:element name="Foundation.Core.GeneralizableElement.isAbstract">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isAbstract"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Operation.isRoot -->
	<xsl:template name="Operation.isRoot">
		<xsl:element name="Foundation.Core.Operation.isRoot">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isRoot"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Operation.isLeaf -->
	<xsl:template name="Operation.isLeaf">
		<xsl:element name="Foundation.Core.Operation.isLeaf">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isLeaf"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Operation.isAbstract -->
	<xsl:template name="Operation.isAbstract">
		<xsl:element name="Foundation.Core.Operation.isAbstract">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isAbstract"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Operation.concurrency -->
	<xsl:template name="Operation.concurrency">
		<xsl:element name="Foundation.Core.Operation.concurrency">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@concurrency"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- isActive -->
	<xsl:template name="Class.isActive">
		<xsl:element name="Foundation.Core.Class.isActive">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isActive"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- visibility -->
	<xsl:template name="ModelElement.visibility">
		<xsl:element name="Foundation.Core.ModelElement.visibility">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@visibility"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- ModelElement.name -->
	<xsl:template name="ModelElement.name">
		<xsl:element name="Foundation.Core.ModelElement.name">
			<xsl:value-of select="@name"/>
		</xsl:element>
	</xsl:template>
	
	<!-- BehavioralFeature.isQuery -->
	<xsl:template name="BehavioralFeature.isQuery">
		<xsl:element name="Foundation.Core.BehavioralFeature.isQuery">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isQuery"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Parameter.kind -->
	<xsl:template name="Parameter.kind">
		<xsl:element name="Foundation.Core.Parameter.kind">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@kind"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.isNavigable -->
	<xsl:template name="AssociationEnd.isNavigable">
		<xsl:element name="Foundation.Core.AssociationEnd.isNavigable">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@isNavigation"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.ordering -->
	<xsl:template name="AssociationEnd.ordering">
		<xsl:element name="Foundation.Core.AssociationEnd.ordering">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@ordering"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.aggregation -->
	<xsl:template name="AssociationEnd.aggregation">
		<xsl:element name="Foundation.Core.AssociationEnd.aggregation">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@aggregation"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.targetScope -->
	<xsl:template name="AssociationEnd.targetScope">
		<xsl:element name="Foundation.Core.AssociationEnd.targetScope">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@targetScope"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- AssociationEnd.changeability -->
	<xsl:template name="AssociationEnd.changeability">
		<xsl:element name="Foundation.Core.AssociationEnd.changeability">
			<xsl:attribute name="xmi.value">
				<xsl:value-of select="@changeability"/>
			</xsl:attribute>
		</xsl:element>
	</xsl:template>
	
	<!-- Expression.body -->
	<xsl:template name="Expression.body">
		<xsl:element name="Foundation.Data_Types.Expression.body">
			<xsl:value-of select="@body"/>
		</xsl:element>
	</xsl:template>
	
	<!-- Stereotype.baseClass -->
	<xsl:template name="Stereotype.baseClass">
		<xsl:element name="Foundation.Extension_Mechanisms.Stereotype.baseClass">
			<xsl:value-of select="@baseClass"/>
		</xsl:element>
	</xsl:template>
	
	<!-- xmi.id -->
	<xsl:template name="xmi.id">
		<!-- xmi.id -->
		<xsl:attribute name="xmi.id">
			<xsl:value-of select="@xmi.id"/>
		</xsl:attribute>
	</xsl:template>
	
	<!-- xmi.idref -->
	<xsl:template name="xmi.idref">
		<xsl:attribute name="xmi.idref">
			<xsl:value-of select="@xmi.idref"/>
		</xsl:attribute>
	</xsl:template>
</xsl:stylesheet>
