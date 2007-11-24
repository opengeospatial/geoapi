/*$************************************************************************************************
 **
 ** $Id$
 **
 ** $URL$
 **
 ** Copyright (C) 2003-2005 Open GIS Consortium, Inc.
 ** All Rights Reserved. http://www.opengis.org/legal/
 **
 *************************************************************************************************/
package org.opengis.util;


/**
 * Monitor the progress of some lengthly operation, and allows cancelation.
 * <p>
 * This interface makes no assumption about the output device. Additionnaly, this
 * interface provides support for non-fatal warning and exception reports.
 * <p>
 * All implementations should be multi-thread safe, even the ones that provide
 * feedback to a user interface thread.
 * <p>
 * Here an example showing how to cancel:
 * <pre><code>
 *      Iterator iterator = null;
 *      try {
 *          float size = size();
 *          float position = 0;
 *          progress.started();
 *          for (iterator = iterator(); !progress.isCanceled() && iterator.hasNext(); progress.progress(++position/size)) {
 *              try {
 *                  Feature feature = (Feature) iterator.next();
 *                  visitor.visit(feature);
 *              }
 *              catch (Exception erp) {
 *                  progress.exceptionOccurred(erp);
 *              }
 *          }
 *          progress.complete();
 *      }
 *      finally {
 *          close (iterator);
 *      }
 * </code></pre>
 * Note the use of try and catch to report exceptions.
 *
 * @since  GeoAPI 2.1
 * @author Martin Desruisseaux
 * @author Jody Garnet
 */
public interface ProgressListener {
    /**
     * Description for the lengthly operation to be reported, or
     * {@code null} if none.
     *
     * @deprecated Please use getTask().toString()
     */
    String getDescription();

    /**
     * Return the current task being performed, or {@code null} if none.
     * <p>
     * It is assumed that if the task is {@code null} applications may simply report
     * that the process is "in progress" or "working" as represented in the
     * current locale.
     *
     * @return task being performed.
     */
    InternationalString getTask();

    /**
     * Set the current task being performed.
     * <p>
     * This task may be user visible and is represented as an
     * {@linkplain InternationalString international string} for locale support.
     *
     * @param task
     */
    void setTask(InternationalString task);

    /**
     * Set the description for the lenghtly operation to be reported. This method is usually
     * invoked before any progress begins. However, it is legal to invoke this method at any
     * time during the operation, in which case the description display is updated without
     * any change to the percentage accomplished.
     *
     * @param description The new description, or {@code null} if none.
     *
     * @deprecated please use setTask
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
     * Releases any resources used by this listener. If the progress were reported in a window,
     * this window may be disposed.
     */
    void dispose();

    /**
     * Is this job canceled?
     */
    boolean isCanceled();

    /**
     * Indicate that progress should is canceled.
     *
     * @todo Is it really listener job to cancel a task?
     */
    void setCanceled(boolean cancel);

    /**
     * Reports a warning. This warning may be {@linkplain java.util.logger.Logger logged}, printed
     * to the {@linkplain System#err standard error stream}, appears in a windows or be ignored,
     * at implementor choice.
     *
     * @param source Name of the warning source, or {@code null} if none. This is typically the
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
