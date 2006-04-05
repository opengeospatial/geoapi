/*
 * Geotools 2 - OpenSource mapping toolkit
 * (C) 2004, Geotools Project Managment Committee (PMC)
 * (C) 2004, Institut de Recherche pour le Développement
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package org.opengis.util;

// J2SE dependencies
import java.io.File;
import java.io.FileFilter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.logging.Logger;


/**
 * List all GeoAPI <code>.class</code> files found in the class path.
 *
 * @version $Id$
 * @author Martin Desruisseaux
 */
final class ClassFinder implements FileFilter, Comparator<File> {
    /**
     * The default root directory, or <code>null</code> if not found.
     * The default root directory is the one where the implementation
     * of the specified class is found, up to the 'org' package.
     */
    private final File rootDirectory;

    /**
     * The root package. Usually "org.opengis".
     */
    private String rootPackage = "org.opengis";

    /**
     * The set of classes found.
     */
    private final Set<Class> classes = new LinkedHashSet<Class>();

    /**
     * Prepare a list of all class found in the classpath.
     *
     * @param prototype An example of the class to search for.
     */
    private ClassFinder(final Class prototype) {
        final URL url = prototype.getClassLoader().getResource(prototype.getName().replace('.','/')+".class");
        if (url==null || !url.getProtocol().trim().equalsIgnoreCase("file")) {
            rootDirectory = null; // bad developer no test not on filesystem
            return;
        }
        String path;
        try {
            path = URLDecoder.decode(url.getPath(), "UTF-8");
        } catch (UnsupportedEncodingException exception) {
            rootDirectory = null;
            return;
        }
        if (path.length()>=3 && path.charAt(0)=='/' && path.charAt(2)==':' ) {
            // Windows drive letter (e.g. /C:). Trim the leading '/'.
            path = path.substring(1);
        }
        File file = new File(path);
        do {
            path = file.getName();
            file = file.getParentFile();
        } while (file!=null && !path.equals("org"));
        rootDirectory = file;
    }

    /**
     * Returns the set of class found in the class path.
     *
     * @param  prototype An example of the class to search for.
     * @return The set of classes found in the class path.
     */
    public static Set<Class> getClasses(final Class prototype) {
        return getClasses(prototype, null);
    }

    /**
     * Returns the set of class found in the class path.
     *
     * @param  prototype An example of the class to search for.
     * @return The set of classes found in the class path.
     */
    public static Set<Class> getClasses(final Class prototype, final String rootPackage) {
        final ClassFinder finder = new ClassFinder(prototype);
        if (rootPackage != null) {
            finder.rootPackage = rootPackage;
        }
        finder.scan(finder.rootDirectory);
        if (finder.classes.isEmpty()) {
            Logger.getLogger("org.geotools").warning("No class found.");
        }
        return finder.classes;
    }
    
    /**
     * Filter the files to includes on the processing. Only the files with the ".class" extensions
     * will be loaded. Directory must be included too if recursive scanning is wanted.
     */
    public boolean accept(final File pathname) {
        final String name = pathname.getName();
        if (pathname.isDirectory()) {
            if (name.equals("org") || name.equals("opengis")) {
                return true;
            }
            // Excludes the GO-1 interfaces for now, and internal packages.
            if (name.equals("go") || name.equals("tools") || name.equals("annotation")) {
                return false;
            }
            // Excludes legacy interfaces.
            if (name.equals("pt") || name.equals("cs") || name.equals("ct") ||
                name.equals("cv") || name.equals("gc") || name.equals("gp"))
            {
                return false;
            }
            return pathname.getPath().replace(File.separatorChar, '.').indexOf(rootPackage) >= 0;
        } else {
            if (name.startsWith("SimpleEnumerationType")) {
                return false;
            }
        }
        if (name.equals("package-info.class")) {
            return false;
        }
        return name.endsWith(".class");
    }

    /**
     * Compares two files for order.
     */
    public int compare(final File f1, final File f2) {
        if (f1.isFile() && f2.isDirectory()) return -1;
        if (f2.isFile() && f1.isDirectory()) return +1;
        return f1.compareTo(f2);
    }
    
    /**
     * Scans the directory and all subdirectory for classes implementing {@link CodeList}.
     */
    private void scan(final File directory) {
        if (directory == null || !directory.exists() || !directory.isDirectory()) {
            Logger.getLogger("org.geotools").warning("No directory to scan: "+directory);
            return; // Just a warning; do not fails.
        }
        final StringBuilder buffer = new StringBuilder();
        final File[] files = directory.listFiles(this);
        Arrays.sort(files, this);
        for (int i=0; i<files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) {
                scan(file);
                continue;
            }
            buffer.setLength(0);
            String name = file.getName();
            final int ext = name.lastIndexOf('.');
            if (ext >= 0) {
                name = name.substring(0, ext);
            }
            buffer.append(name);
            while ((file=file.getParentFile()) != null) {
                if (file.equals(rootDirectory)) {
                    break;
                }
                buffer.insert(0, '.');
                buffer.insert(0, file.getName());
            }
            name = buffer.toString();
            try {
                classes.add(Class.forName(name));
            } catch (ClassNotFoundException exception) {
                Logger.getLogger("org.geotools").warning("Class not found: "+name);
            }
        }
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
    public static String getShortName(Class classe) {
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
}
