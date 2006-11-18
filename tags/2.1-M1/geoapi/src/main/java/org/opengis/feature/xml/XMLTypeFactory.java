package org.opengis.feature.xml;

import org.opengis.feature.type.FeatureCollectionType;
import org.opengis.feature.type.FeatureType;
import org.opengis.feature.type.TypeFactory;

/**
 * Allows for construction of the basic XML types Choice and Sequence.
 * <p>
 * These XML concepts are captured for your convience, because XML Schema (in
 * particular GML) represents an important target for those implementing this
 * </p>
 * The following binding is necacessary by not sufficient:
 * <ul>
 * <li>List is required for Sequence, but does not denote Sequence in and of
 * itself.
 * </ul>
 * <p>
 * The following represents our understanding:
 * <ul>
 * <li><code>all</code>:ComplexType<Collection> you needs all of the
 * contents, but order is not significant
 * <li><code>sequence</code>: Sequence<List>, aka ComplexType<List>
 * <li><code>choice</code>: ComplexType<Set>, where all properties are
 * optiona (0:1 cardinality), with a restrictions that the list of properties is
 * of length 1.
 * </ul>
 * <p>
 * WARNING: This package defines no new modeling power. It uses the existing API
 * of the feature model to communicate the additional restrictions placed on
 * data content being held in a XML system.
 * </p>
 * <p>
 * What this means? Do not do instanceof Sequence checks you do not have to! (It
 * is only to help those working with XMLSchema directly)
 * </p>
 * 
 * @author Jody Garnett
 */
public interface XMLTypeFactory  extends TypeFactory {

	/** TODO: Method signature based on initial implementation */
	SequenceType createSequenceType();

	/** TODO: Method signature based on initial implementation */
	ChoiceType createChoiceType();

	/** The FeatureType returned is also a SequenceType. */
	FeatureType createSequenceFeatureType();

	/** The FeatureCollectionType returned is also a SequenceType. */
	FeatureCollectionType createSequenceFeatureCollectionType();
}
