/*
 * Geotools 2 - OpenSource mapping toolkit
 * (C) 2003, Geotools Project Managment Committee (PMC)
 * (C) 2001, Institut de Recherche pour le D�veloppement
 * (C) 1999, P�ches et Oc�ans Canada
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



/**
 * Monitor the progress of some lengthly operation, and allows cancelation.
 * <p>
 * This interface makes no assumption about the output device. Additionnaly, this
 * interface provides support for non-fatal warning and exception reports.
 * <p>
 * All implementations should be multi-thread safe, even the that provide
 * feedback to a user interface thread.
 * <p>
 * Here an example showing how to cancel:
 * <pre><code>
 *      Iterator iterator = null;
 *      try{
 *          float size = size();
 *          float position = 0;
 *          progress.started();
 *          for( iterator = iterator(); !progress.isCanceled() && iterator.hasNext(); progress.progress( (position++)/size )){
 *              try {
 *                  Feature feature = (Feature) iterator.next();
 *                  visitor.visit(feature);
 *              }
 *              catch( Exception erp ){
 *                  progress.exceptionOccurred( erp );
 *              }
 *          }
 *          progress.complete();
 *      }
 *      finally {
 *          close( iterator );
 *      }
 * </code></pre>
 * Note the use of try and catch to report exceptions.
 * 
 * @author Martin Desruisseaux
 */
public interface ProgressListener {
    /**
     * Description for the lengthly operation to be reported, or
     * {@code null} if none.
     * @deprecated Please use getTask().toString()
     */
    String getDescription();
    
    /**
     * Return the current task being performed, or null if none.
     * <p>
     * It is assumed that if the task is null applications may simply report
     * that the process is "in progress" or "working" as represented in the
     * current locacle.
     * </p>
     * @return task being performed.
     */
    InternationalString getTask();
    
    /**
     * Set the current task being performed.
     * <p>
     * This task may be user visible and is represented as an
     * InternationalStirng for locale support.
     * </p>
     * @param task
     */
    void setTask( InternationalString task );
    
    /**
     * Set the description for the lenghtly operation to be reported. This method is usually
     * invoked before any progress begins. However, it is legal to invoke this method at any
     * time during the operation, in which case the description display is updated without
     * any change to the percentage accomplished.
     * @deprecated please use setTask
     * @param description The new description, or {@code null} if none.
     */
    void setDescription(String description);

    /**
     * Notifies this listener that the operation begins.
     */
    void started();

    /**
     * Notifies this listener of progress in the lengthly operation. Progress are reported
     * as a value between 0 and 100 inclusive. Values out of bounds will be clamped.
     */
    void progress(float percent);

    /**
     * Notifies this listener that the operation has finished. The progress indicator will
     * shows 100% or disaspears, at implementor choice. If warning messages were pending,
     * they will be displayed now.
     */
    void complete();

    /**
     * Release any resources used by this listener. If the progress were reported in a window,
     * this window may be disposed.
     */
    void dispose();

    /**
     * Is this job canceled?
     */
    boolean isCanceled();
    
    /**
     * Indicate that progress should is canceled.
     */
    void setCanceled(boolean cancel);    
    
    /**
     * Reports a warning. This warning may be printed to the {@linkplain System#err standard error
     * stream}, appears in a windows or be ignored, at implementor choice.
     *
     * @param source The source of the warning, or {@code null} if none. This is typically the
     *        filename in process of being parsed.
     * @param margin Text to write on the left side of the warning message, or {@code null} if none.
     *        This is typically the line number where the error occured in the {@code source} file.
     * @param warning The warning message.
     */
    void warningOccurred(String source, String margin, String warning);

    /**
     * Reports an exception. This method may prints the stack trace to the {@linkplain System#err
     * standard error stream} or display it in a dialog box, at implementor choice.
     */
    void exceptionOccurred(Throwable exception);
}