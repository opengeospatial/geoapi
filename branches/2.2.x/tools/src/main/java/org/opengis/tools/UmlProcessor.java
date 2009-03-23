/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003 Open GIS Consortium, Inc. All Rights Reserved. http://www.opengis.org/Legal/
 **
 *************************************************************************************************/
package org.opengis.tools;

// J2SE dependencies
import java.util.Set;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;

// Annotation processing tools
import com.sun.mirror.type.TypeMirror;
import com.sun.mirror.type.DeclaredType;
import com.sun.mirror.type.PrimitiveType;
import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.Declaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.util.DeclarationVisitors;
import com.sun.mirror.util.SimpleDeclarationVisitor;

// OpenGIS dependencies
import org.opengis.util.CodeList;
import org.opengis.annotation.UML;


/**
 * Base classe for annotation processors working on the {@link UML} tag.
 *
 * @author Martin Desruisseaux
 */
public abstract class UmlProcessor extends SimpleDeclarationVisitor
        implements AnnotationProcessor, AnnotationProcessorFactory
{
    /**
     * Annotations supported by this annotation processor.
     */
    private static final Collection<String> supportedAnnotations = Collections.unmodifiableCollection(
            Arrays.asList("org.opengis.annotation.UML"));

    /**
     * Options supported by this annotation processor.
     */
    private static final Collection<String> supportedOptions = Collections.emptySet();

    /**
     * Declarations of the annotation types to be processed, or {@code null} if not yet defined.
     * This field is initialized by {@link #getProcessorFor getProcessorFor}.
     */
    protected Set<AnnotationTypeDeclaration> types;

    /**
     * Environment to use during processing, or {@code null} if not yet defined.
     * This field is initialized by {@link #getProcessorFor getProcessorFor}.
     */
    protected AnnotationProcessorEnvironment environment;

    /**
     * Creates a new instance of {@code UmlProcessor}.
     */
    public UmlProcessor() {
    }

    /**
     * Returns the options recognized by this factory or by any of the processors it may create.
     */
    public Collection<String> supportedOptions() {
        return supportedOptions;
    }

    /**
     * Returns the names of the annotation types supported by this factory.
     */
    public Collection<String> supportedAnnotationTypes() {
        return supportedAnnotations;
    }

    /**
     * Returns an annotation processor for a set of annotation types. This very simple factory
     * allow the creation of at most one processor instance.
     *
     * @param  type        Declarations of the annotation types to be processed.
     * @param  environment Environment to use during processing.
     * @return This annotation processor.
     * @throws IllegalStateException if this method has already been invoked on this instance.
     */
    public AnnotationProcessor getProcessorFor(final Set<AnnotationTypeDeclaration> types,
                                               final AnnotationProcessorEnvironment environment)
    {
        if (this.types != null || this.environment != null) {
            throw new IllegalStateException();
        }
        this.types       = types;
        this.environment = environment;
        return this;
    }

    /**
     * Process all program elements supported by this annotation processor. The default implementation
     * invokes the {@code visitFoo} methods defined in {@link SimpleDeclarationVisitor} for every
     * declaration specified in the {@linkplain AnnotationProcessorEnvironment environment}.
     */
    public void process() {
        for (final TypeDeclaration declaration : environment.getSpecifiedTypeDeclarations()) {
            declaration.accept(DeclarationVisitors.getSourceOrderDeclarationScanner(this, DeclarationVisitors.NO_OP));
        }
    }

    /**
     * Returns the UML identifier for the specified element, or {@code null}
     * if the specified element is not part of the UML model.
     */
    protected static String getUmlIdentifier(final AnnotatedElement element) {
        return getUmlIdentifier(element.getAnnotation(UML.class));
    }

    /**
     * Returns the UML identifier for the specified element, or {@code null}
     * if the specified element is not part of the UML model.
     */
    protected static String getUmlIdentifier(final Declaration element) {
        return getUmlIdentifier(element.getAnnotation(UML.class));
    }

    /**
     * Returns the UML identifier for the specified element, or {@code null}
     * if the specified element is not part of the UML model.
     */
    protected static String getUmlIdentifier(final TypeMirror element) {
        return (element instanceof DeclaredType) ? getUmlIdentifier(((DeclaredType) element).getDeclaration()) : null;
    }

    /**
     * Returns the UML identifier, or {@code null} if none.
     */
    private static String getUmlIdentifier(final UML uml) {
        if (uml != null) {
            String identifier = uml.identifier();
            /*
             * If there is two or more UML identifiers collapsed in only one
             * Java method, keep only the first identifier (which is usually
             * the main attribute).
             */
            final int split = identifier.indexOf(',');
            if (split >= 0) {
                identifier = identifier.substring(0, split);
            }
            return identifier;
        }
        return null;
    }

    /**
     * Returns {@code true} if the specified declaration is a {@link CodeList}.
     */
    protected final boolean isCodeList(final TypeDeclaration declaration) {
        return (declaration instanceof ClassDeclaration) &&
               CodeList.class.isAssignableFrom(getClass(declaration));
    }

    /**
     * Returns the class object for the specified declaration. If the class can't be found,
     * then this method returns {@link Void#TYPE}. The later is not quite correct (but do
     * the trick for this package purpose), which is why this method is package-private.
     */
    final Class getClass(final TypeDeclaration declaration) {
        try {
            return Class.forName(declaration.getQualifiedName());
        } catch (ClassNotFoundException e) {
            environment.getMessager().printError("Class not found: " + e.getLocalizedMessage());
            return Void.TYPE;
        }
    }

    /**
     * Returns the class object for the specified declaration. If the class can't be found,
     * then this method returns {@link Void#TYPE}. The later is not quite correct (but do
     * the trick for this package purpose), which is why this method is package-private.
     */
    final Class getClass(final TypeMirror type) {
        if (type instanceof PrimitiveType) {
            switch (((PrimitiveType) type).getKind()) {
                case BOOLEAN : return Boolean  .TYPE;
                case BYTE    : return Byte     .TYPE;
                case SHORT   : return Short    .TYPE;
                case INT     : return Integer  .TYPE;
                case LONG    : return Long     .TYPE;
                case CHAR    : return Character.TYPE;
                case FLOAT   : return Float    .TYPE;
                case DOUBLE  : return Double   .TYPE;
                default      : throw new AssertionError(type);
            }
        }
        if (type instanceof DeclaredType) {
            return getClass(((DeclaredType) type).getDeclaration());
        }
        return Void.TYPE;
    }

    /**
     * Returns a short class name for the specified class. This method will
     * omit the package name.  For example, it will return "String" instead
     * of "java.lang.String" for a {@link String} object. It will also name
     * array according Java language usage,  for example "double[]" instead
     * of "[D".
     *
     * @param  classe The object class (may be <code>null</code>).
     * @return A short class name for the specified object.
     */
    protected static String getShortName(Class classe) {
        if (classe == null) {
            return "<*>";
        }
        int dimension = 0;
        Class el;
        while ((el = classe.getComponentType()) != null) {
            classe = el;
            dimension++;
        }
        String name = classe.getName();
        final int lower = name.lastIndexOf('.');
        final int upper = name.length();
        name = name.substring(lower+1, upper).replace('$','.');
        if (dimension != 0) {
            StringBuffer buffer = new StringBuffer(name);
            do {
                buffer.append("[]");
            } while (--dimension != 0);
            name = buffer.toString();
        }
        return name;
    }

    /**
     * Print an error message after the failure to open a file.
     */
    final void printError(final IOException exception) {
        String name = exception.getClass().getName();
        name = name.substring(name.lastIndexOf('.') + 1);
        environment.getMessager().printError("Unable to create output files. The cause is " +
                                             name + ": " + exception.getLocalizedMessage());
    }
}
