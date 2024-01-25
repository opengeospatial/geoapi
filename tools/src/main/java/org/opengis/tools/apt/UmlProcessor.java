/*
 *    GeoAPI - Java interfaces for OGC/ISO standards
 *    Copyright © 2006-2023 Open Geospatial Consortium, Inc.
 *    http://www.geoapi.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.opengis.tools.apt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.tools.Diagnostic;
import javax.lang.model.util.Types;
import javax.lang.model.util.Elements;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.DeclaredType;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;


/**
 * Provides convenience methods for annotation processors working on the
 * {@link org.opengis.annotation.UML} annotation.
 *
 * @author  Martin Desruisseaux (Geomatys)
 * @version 3.1
 * @since   2.0
 */
abstract class UmlProcessor extends AbstractProcessor {
    /**
     * The fully qualified name of the {@link org.opengis.annotation.UML} annotation.
     */
    static final String UML_CLASSNAME = "org.opengis.annotation.UML";

    /**
     * A few base types of special interest.
     */
    private final Map<Classes,TypeMirror> classes;

    /**
     * The {@link org.opengis.annotation.UML} methods.
     */
    private final Map<UMLMember,ExecutableElement> umlMembers;

    /**
     * Set to {@code true} by {@link #init(ProcessingEnvironment)} methods if initialization failed.
     * Also set to {@code true} after {@code process} execution for preventing the processor to be
     * executed again on the second round.
     */
    boolean skip;

    /**
     * Creates a new instance of {@code UmlProcessor}.
     * Subclasses must supply a public no-argument constructor.
     */
    UmlProcessor() {
        classes    = new EnumMap<>(Classes.class);
        umlMembers = new EnumMap<>(UMLMember.class);
    }

    /**
     * Initializes this processor.
     *
     * @param processingEnv Provides access to the tools framework.
     */
    @Override
    public void init(final ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        final Elements utils = processingEnv.getElementUtils();
        final Types typeUtils = processingEnv.getTypeUtils();
        for (final Classes c : Classes.values()) {
            final TypeElement e = utils.getTypeElement(c.classname);
            if (e == null) {
                if (c.isPending) {
                    continue;               // Interfaces from geoapi-pending module are optional.
                }
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Class not found: " + c.classname);
                skip = true;
                return;
            }
            classes.put(c, typeUtils.erasure(e.asType()));
            if (c == Classes.UML) {
                final Map<String,Element> members = new HashMap<>();
                for (final Element m : e.getEnclosedElements()) {
                    members.put(m.getSimpleName().toString(), m);
                }
                for (final UMLMember m : UMLMember.values()) {
                    final Element ex = members.get(m.methodName);
                    if (ex == null) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Method not found: UML." + m.methodName);
                        skip = true;
                        return;
                    }
                    umlMembers.put(m, (ExecutableElement) ex);
                }
            }
        }
    }

    /**
     * Processes all types given to the compiler (excluding packages).
     *
     * @param elements Classes or interfaces given to the compiler.
     * @throws IOException if an error occurred while generating a report.
     */
    abstract void process(final TypeElement[] elements) throws IOException;

    /**
     * Processes all classes and interfaces, no matter in annotated by
     * {@link org.opengis.annotation.UML} or not.
     *
     * @param  annotations The UML annotation to process (currently ignored).
     * @param  roundEnv Information about current round.
     * @return {@code false}, since this method does not prevent other processor to process {@code UML} annotations.
     */
    @Override
    public final boolean process(final Set<? extends TypeElement> annotations, final RoundEnvironment roundEnv) {
        if (!skip) {
            skip = true;                // For preventing additional execution.
            final Set<? extends Element> elements = roundEnv.getRootElements();
            TypeElement[] types = new TypeElement[elements.size()];
            int count = 0;
            for (final Element e : elements) {
                if (isTypeElement(e)) {
                    types[count++] = (TypeElement) e;
                }
            }
            types = Arrays.copyOf(types, count);
            try {
                process(types);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                        "Unable to create output files. The cause is " +
                        e.getClass().getSimpleName() + ": " + e.getLocalizedMessage());
            }
        }
        return false;
    }

    /**
     * Returns a writer for a file in the current directory using UTF-8 encoding.
     *
     * @param  relativePath The path of the file to create, relative to the current directory.
     * @return the writer.
     * @throws IOException if the writer cannot be created.
     */
    static Writer openWriter(final String relativePath) throws IOException {
        return new OutputStreamWriter(new FileOutputStream(relativePath), "UTF-8");
    }

    /**
     * Returns {@code true} if the given type is a subtype of the given base type.
     * If the given base type is unknown, then this method conservatively returns {@code null}.
     */
    final boolean isSubtype(final TypeMirror type, final Classes baseType) {
        final TypeMirror base = classes.get(baseType);
        return (base != null) && processingEnv.getTypeUtils().isSubtype(type, base);
    }

    /**
     * Returns {@code true} if the given element is a class or an interface.
     * This is used as a substitute to {@code (e instanceof ElementType)}
     * since the latter is documented as non-reliable in JDK javadoc.
     */
    private static boolean isTypeElement(final Element e) {
        final ElementKind kind = e.getKind();
        return kind.isClass() || kind.isInterface();
    }

    /**
     * Returns the package name of the given type.
     *
     * @param  e The class or interface for which to get the package name.
     * @return the package name of the given type.
     */
    final String getPackageName(final Element e) {
        return processingEnv.getElementUtils().getPackageOf(e).getQualifiedName().toString();
    }

    /**
     * Returns the fully qualified name of the given field or method.
     *
     * @param  e A field or a method.
     * @return the qualified name of the given field or method.
     */
    static String getQualifiedName(final Element e) {
        String name = e.getSimpleName().toString();
        final Element c = e.getEnclosingElement();
        if (c instanceof TypeElement) {
            name = ((TypeElement) c).getQualifiedName().toString() + '.' + name;
        }
        return name;
    }

    /**
     * Returns the name of the given element, completed with the name of the outer class is any.
     * The check for outer interface is mostly for {@code CodeList.Filter}.
     *
     * @param  e The type or member for which to get the name relative to the package.
     * @return the element name, prefixed with its enclosing class if any.
     */
    static String getRelativeName(Element e) {
        String classname = e.getSimpleName().toString();
        while (isTypeElement(e = e.getEnclosingElement())) {
            classname = e.getSimpleName().toString() + '.' + classname;
        }
        return classname;
    }

    /**
     * Returns the class or interface that contains the given field or method.
     * If the given element is already a class or interface, then it is returned directly.
     *
     * @param  e The element for which to get the enclosing type element.
     * @return the enclosing type element (may be {@code e} itself).
     */
    static TypeElement getTypeElement(Element e) {
        while (!isTypeElement(e)) {
            e = e.getEnclosingElement();
        }
        return (TypeElement) e;
    }

    /**
     * Gets all public or protected non-deprecated fields and methods of the given class or interface.
     * If two members of the same name are defined, the one with a UML annotation will be selected
     * (there should be only one).
     *
     * @param  element The type of interface for which to get all declared members.
     * @return the public non-deprecated members, in declaration order.
     */
    final Collection<Element> getMembers(final TypeElement element) {
        final Elements utils = processingEnv.getElementUtils();
        final Map<String,Element> members = new LinkedHashMap<>();
        for (final Element member : element.getEnclosedElements()) {
            if (!utils.isDeprecated(member)) {
                final Set<Modifier> modifiers = member.getModifiers();
                if (modifiers.contains(Modifier.PUBLIC) || modifiers.contains(Modifier.PROTECTED)) {
                    final String name = member.getSimpleName().toString();
                    final Element old = members.put(name, member);
                    if (old != null) {
                        // Found a duplicated member. Restore the first member, unless
                        // the new one has a UML annotation while the older one had none.
                        final boolean oldHasUML = getUML(old)    != null;
                        final boolean newHasUML = getUML(member) != null;
                        if (oldHasUML || !newHasUML) {
                            members.put(name, old);
                            if (oldHasUML && newHasUML) {
                                // Preserve also the new member using a generated key.
                                int n = 0;
                                String key;
                                do key = name + '$' + (++n);
                                while (members.containsKey(key));
                                members.put(key, member);
                            }
                        }
                    }
                }
            }
        }
        return members.values();
    }

    /**
     * Returns the UML annotation of the given element, or {@code null} if none.
     *
     * @param  element The element on which to get the UML annotation.
     * @return the UML annotation, or {@code null} if none.
     */
    final AnnotationMirror getUML(final Element element) {
        for (final AnnotationMirror e :element.getAnnotationMirrors()) {
            if (((TypeElement) e.getAnnotationType().asElement()).getQualifiedName().toString().equals(UML_CLASSNAME)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Returns the display name for the given UML identifier.
     *
     * @param  uml The UML annotation for which to get the display name, or {@code null}.
     * @return the display name, or {@code null} if the given UML annotation was null.
     */
    final String getDisplayName(final AnnotationMirror uml) {
        if (uml != null) {
            final AnnotationValue a = uml.getElementValues().get(umlMembers.get(UMLMember.IDENTIFIER));
            if (a != null) {
                String identifier = (String) a.getValue();
                /*
                 * If there are two or more UML identifiers collapsed in only one Java method,
                 * keep only the first identifier (which is usually the main attribute).
                 */
                final int split = identifier.indexOf(',');
                if (split >= 0) {
                    identifier = identifier.substring(0, split);
                }
                return identifier.substring(identifier.lastIndexOf('.') + 1);
            }
        }
        return null;
    }

    /**
     * Returns the display name of the specification attribute in the given UML.
     * The returned name include the specification version number, if specified.
     *
     * @param  uml The UML annotation for which to get the specification, or {@code null}.
     * @return the specification name, or {@code null} if the given UML annotation was null.
     */
    final String getSpecification(final AnnotationMirror uml) {
        if (uml != null) {
            final Map<? extends ExecutableElement, ? extends AnnotationValue> values = uml.getElementValues();
            AnnotationValue a = values.get(umlMembers.get(UMLMember.SPECIFICATION));
            if (a != null) {
                String name = ((Element) a.getValue()).getSimpleName().toString();
                name = name.replace("ISO_","ISO ").replace("OGC_","OGC ").replace('_', '-');
                values.get(umlMembers.get(UMLMember.VERSION));
                a = values.get(umlMembers.get(UMLMember.VERSION));
                if (a != null) {
                    final int version = ((Number) a.getValue()).intValue();
                    if (version != 0) {
                        name = name + ':' + version;
                    }
                }
                return name;
            }
        }
        return null;
    }

    /**
     * Returns {@code true} if the given type is an array, a collection, a map or any other type
     * that may hold more than one value.
     *
     * @param  type  the type to test for multi-occurrence.
     * @return {@code true} if the given type can hold more than one value.
     */
    final boolean isMultiOccurrence(final TypeMirror type) {
        if (type.getKind() == TypeKind.ARRAY) {
            return true;
        }
        for (final Classes c : Classes.MULTI_OCCURRENCE) {
            if (isSubtype(type, c)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if the given element override an element from a parent interface.
     * To be considered as an override, the two methods must also have the same UML identifier (if any).
     *
     * @param element The element to test for overriding.
     * @return {@code true} if the given element override an element from a parent.
     */
    final boolean overrides(final ExecutableElement element) {
        for (final TypeMirror t : ((TypeElement) element.getEnclosingElement()).getInterfaces()) {
            if (t.getKind() == TypeKind.DECLARED) {
                if (overrides(element, (TypeElement) ((DeclaredType) t).asElement())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Implementation of {@link #overrides(ExecutableElement)} to be invoked recursively.
     */
    private boolean overrides(final ExecutableElement element, final TypeElement parent) {
        final TypeElement enclosing = (TypeElement) element.getEnclosingElement();
        final Elements utils = processingEnv.getElementUtils();
        for (final Element member : parent.getEnclosedElements()) {
            if (member.getKind() == ElementKind.METHOD) {
                if (utils.overrides(element, (ExecutableElement) member, enclosing)) {
                    final String u1 = getDisplayName(getUML(element));
                    final String u2 = getDisplayName(getUML(member));
                    if (u1 == u2 || (u1 != null && u1.equals(u2))) {
                        return true;
                    }
                }
            }
        }
        for (final TypeMirror t : parent.getInterfaces()) {
            if (t.getKind() == TypeKind.DECLARED) {
                if (overrides(element, (TypeElement) ((DeclaredType) t).asElement())) {
                    return true;
                }
            }
        }
        return false;
    }
}
