package org.opengis.feature.type;

import java.lang.reflect.InvocationTargetException;

import org.opengis.feature.Attribute;


/**
 * Indicating an implementation of an operation for a specific type.
 * <p>
 * This class carries the ComplexType specific information requried for using an
 * operation. Name, type and evaulation are defined.
 * <p>
 * Please see the description of OperationType for more guidelines on capturing
 * operations for use with your model.
 * </p>
 *
 * @author Jody Garnett, Refractions Research
 */
public interface OperationDescriptor extends PropertyDescriptor {
    /** Indicates the OpperationType of this attribute */
    OperationType getType();

    /**
     * Indicates Name of defined operation in a ComplexType, this method may
     * never return a null value.
     */
    Name getName();

    /**
     * Indicates if invoke may be called.
     * <p>
     * In order allow for faithful modeling of a software system we will need
     * construct models dynamically at runtime, possibly when no implementation
     * has been discouvered for the required operations. (for example we may be
     * modeling a schema where the operations are only available when the
     * information is executed on a remote web processing service. We still need
     * to track the operations, even those we cannot execute them in the current
     * environment.
     * </p>
     *
     * @return true if invoke may be called.
     */
    boolean isImplemented();

    /**
     * Call the operation with an attribtue and a set of parameters.
     * <p>
     * The state of the operation may be used and / or updated during the
     * execution of the operation.
     * </p>
     * <p>
     * Please check to ensure that isImplemented returns <code>true</code>
     * before calling invoke.
     *
     * @param target
     *            This is the attribute used for context when evaulating the
     *            operation, the target may have it's state changed over the
     *            course of executation
     * @param params Parameters used to execute the opperation
     * @return the result of the operation
     * @throws InvoationTargetException
     *             if an error occured while processing
     */
    Object invoke(Attribute target, Object params[])
            throws InvocationTargetException;
}
