package org.opengis.feature.type;

import java.util.Set;

import org.opengis.filter.Filter;
import org.opengis.util.InternationalString;

public interface AttributeTypeBuilder<B,T extends AttributeType<B>> {
    
    //
    // Dependences Injection
    // (aka what we need to function)
    //

    /**
     * Returns the underlying type factory.
     */
    TypeFactory getTypeFactory();

    /**
     * Sets the underyling type factory.
     */
    void setTypeFactory(TypeFactory factory);

    //
    // Use
    //
    
    /**
     * Initializes the state of the builder, leaving the builder in the same
     * state as after it is instantiated.
     */
    void init();
    
    /**
     * Initializes the state of the builder based on a previously built type.
     * <p>
     *  This method is useful when extending another copying another type. 
     * </p>
     */
    void init(T type);
    
    /**
     * Create a ComplexType based on the configuration of this builder.
     * <p>
     * This method will use the provided TypeFactory to create an ComplexType
     * based on the builder configuration.
     */
    T build();
    
    //
    // State
    //
    // These methods capture state used in helping produce
    // objects.
    //
    /**
     * Sets the namespace to be used when constructing type and attribute names.
     */
    void setNamespaceURI(String uri);

    /**
     * Returns The namespace to be used when constructing type and attribute
     * names.
     */
    String getNamespaceURI();

    /**
     * Sets the name of the type, combined with getNamespaceURI to produce the
     * TypeName.
     * <p>
     * This value must be set prior to calling create()
     * </p>
     */
    void setName(String name);

    /**
     * Returns the name component of the type to be created.
     */
    String getName();

//    /**
//     * The TypeName for the type to be created.
//     * 
//     * @return
//     */
//    TypeName getTypeName();
//
//    /**
//     * Provide a TypeName for the type to be created.
//     * <p>
//     * This method is useful when working with TypeNames created by the
//     * application using the library (ie an application specific
//     * implementation).
//     * </p>
//     * 
//     * @param name
//     * @return
//     */
//    void setTypeName(TypeName name);

    /**
     * Sets the class this type is bound to.
     * 
     * @param binding A java class.
     */
    void setBinding(Class<B> binding);
    
    /**
     * @return the class that this type is bound to.
     */
    Class<B> getBinding();
    
    /**
     * 
     * @return The description of the type.
     */
    InternationalString getDescription();
    
    /**
     * Sets the description of the type.
     * 
     * @param description The description as an internationalized string.
     */
    void setDescription(InternationalString description);
    
    /**
     * Sets the super type of created types.
     * 
     * @see AttributeType#getSuper()
     */
    <E extends T> void setSuper(E superType);

    /**
     * Sets the super type of created types.
     * 
     * @see AttributeType#getSuper()
     */
    <E extends T> E getSuper();

    /**
     * Adds an additional restriction on the created type.
     */
    void addRestriction(Filter restriction);
    
    /**
     * Sets additional restrictions on created types.
     * 
     * @see AttributeType#getRestrictions()
     */
    void setRestrictions(Set<Filter> restrictions);

    /**
     * Returns additional restrictions on created types.
     * 
     * @see AttributeType#getRestrictions()
     */
    Set<Filter> getRestrictions();

    /**
     * Sets the nillable property of any attributes added subsequentley.
     * <p>
     * This method will not effect any previously added attributes.
     * </p>
     * 
     * @see AttributeDescriptor#isNillable()
     */
    void setNillable(boolean isNillable);
    
    /**
     * Returns the nillable property of attributes.
     */
    boolean isNillable();
    
    /**
     * Sets the abstract property of created types.
     * 
     * @see AttributeType#isAbstract()
     */
    void setAbstract(boolean isAbstract);

    /**
     * Returns the abstract property of created types.
     * 
     * @see AttributeType#isAbstract()
     */
    boolean isAbstract();

}
