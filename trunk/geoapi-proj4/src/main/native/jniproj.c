/*
 * Copyright header to be determined.
 */

#include "proj_config.h"

#ifdef JNI_ENABLED

#include <math.h>
#include <string.h>
#include "projects.h"
#include "org_proj4_PJ.h"
#include <jni.h>

/*!
 * \brief
 * Returns the Proj4 release number.
 *
 * \param env   - The JNI environment.
 * \param class - The class from which this method has been invoked.
 * \return The Proj4 release number, or NULL.
 */
JNIEXPORT jstring JNICALL Java_org_proj4_PJ_getVersion
  (JNIEnv *env, jclass class)
{
    const char *desc = pj_get_release();
    return (desc) ? (*env)->NewStringUTF(env, desc) : NULL;
}

/*!
 * \brief
 * Allocates a new PJ structure from a definition string.
 *
 * \param env - The JNI environment.
 * \param class - The class from which this method has been invoked.
 * \param definition - The string definition to be given to Proj4.
 * \return The address of the new PJ structure, or 0 in case of failure.
 */
JNIEXPORT jlong JNICALL Java_org_proj4_PJ_allocatePJ
  (JNIEnv *env, jclass class, jstring definition)
{
    const char *def_utf = (*env)->GetStringUTFChars(env, definition, NULL);
    if (!def_utf) return 0;
    PJ *pj = pj_init_plus(def_utf);
    (*env)->ReleaseStringUTFChars(env, definition, def_utf);
    return (jlong) pj;
}

/*!
 * \brief
 * Internal method returning the address of the PJ structure wrapped by the given Java object.
 * This function looks for a field named "ptr" and of type "long" (Java signature "J") in the
 * given object.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The address of the PJ structure, or NULL if the operation fails (for example
 *         because the "ptr" field was not found).
 */
PJ *getPJ(JNIEnv *env, jobject object)
{
    jfieldID id = (*env)->GetFieldID(env, (*env)->GetObjectClass(env, object), "ptr", "J");
    if (!id) return NULL;
    return (PJ*) (*env)->GetLongField(env, object, id);
}

/*!
 * \brief
 * Returns the definition string.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The definition string.
 */
JNIEXPORT jstring JNICALL Java_org_proj4_PJ_getDefinition
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    if (pj)
    {
        const char *desc = pj_get_def(pj, 0);
        if (desc)
        {
            return (*env)->NewStringUTF(env, desc);
        }
    }
    return NULL;
}

/*!
 * \brief
 * Returns the description associated to the PJ structure.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The description associated to the PJ structure.
 */
JNIEXPORT jstring JNICALL Java_org_proj4_PJ_toString
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    if (pj)
    {
        const char *desc = pj->descr;
        if (desc)
        {
            return (*env)->NewStringUTF(env, desc);
        }
    }
    return NULL;
}

/*!
 * \brief
 * Returns the semi-major axis length.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The semi-major axis length.
 */
JNIEXPORT jdouble JNICALL Java_org_proj4_PJ_getSemiMajorAxis
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return pj ? pj->a_orig : NAN;
}

/*!
 * \brief
 * Computes the semi-minor axis length from the semi-major axis length and the excentricity
 * squared.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The semi-minor axis length.
 */
JNIEXPORT jdouble JNICALL Java_org_proj4_PJ_getSemiMinorAxis
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    if (!pj) return NAN;
    double a = pj->a_orig;
    return sqrt(a*a * (1.0 - pj->es_orig));
}

/*!
 * \brief
 * Computes the inverse flattening from the excentricity squared.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The inverse flattening.
 */
JNIEXPORT jdouble JNICALL Java_org_proj4_PJ_getInverseFlattening
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return pj ? 1.0/(1.0 - sqrt(pj->one_es)) : NAN;
}

/*!
 * \brief
 * Returns JNI_TRUE if the ellipsoid is actually a sphere, or JNI_FALSE otherwise.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return Whatever the ellipsoid is a sphere.
 */
JNIEXPORT jboolean JNICALL Java_org_proj4_PJ_isSphere
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return (pj && pj->es == 0) ? JNI_TRUE : JNI_FALSE;
}

/*!
 * \brief
 * Returns JNI_TRUE if the coordinate reference system is geographic, or JNI_FALSE otherwise.
 * Note that the Proj4 function already checks against NULL value, so we don't need to perform
 * this check again in this method body.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return Whatever the CRS is geographic.
 */
JNIEXPORT jboolean JNICALL Java_org_proj4_PJ_isGeographic
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return pj_is_latlong(pj) ? JNI_TRUE : JNI_FALSE;
}

/*!
 * \brief
 * Returns JNI_TRUE if the coordinate reference system is geocentric, or JNI_FALSE otherwise.
 * Note that the Proj4 function already checks against NULL value, so we don't need to perform
 * this check again in this method body.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return Whatever the CRS is geocentric.
 */
JNIEXPORT jboolean JNICALL Java_org_proj4_PJ_isGeocentric
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return pj_is_geocent(pj) ? JNI_TRUE : JNI_FALSE;
}

/*!
 * \brief
 * Returns an array of character indicating the direction of each axis.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The axis directions.
 */
JNIEXPORT jcharArray JNICALL Java_org_proj4_PJ_getAxisDirections
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    if (pj) {
        int length = sizeof(pj->axis) / sizeof(char);
        jcharArray array = (*env)->NewCharArray(env, length);
        if (array) {
            jchar* axis = (*env)->GetCharArrayElements(env, array, NULL);
            if (axis) {
                // Don't use memcp because the type may not be the same.
                int i;
                for (i=0; i<length; i++) {
                    axis[i] = pj->axis[i];
                }
                (*env)->ReleaseCharArrayElements(env, array, axis, 0);
            }
            return array;
        }
    }
    return NULL;
}

/*!
 * \brief
 * Longitude of the prime meridian measured from the Greenwich meridian, positive eastward.
 *
 * \param env    - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \return The prime meridian longitude, in degrees.
 */
JNIEXPORT jdouble JNICALL Java_org_proj4_PJ_getGreenwichLongitude
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    return (pj) ? (pj->from_greenwich)*(180/M_PI) : 0.0;
}

/*!
 * \brief
 * Transforms in-place the coordinates in the given array.
 *
 * \param env    - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 * \param target - The target CRS.
 * \param hasZ   - TRUE if the given coordinate array is two-dimensional (with z component),
 *                 or FALSE if two-dimensional.
 * \param coordinates - The coordinates to transform, packed as (x,y) or (x,y,z) tuples.
 * \param offset - Offset of the first coordinate in the given array.
 * \param numPts - Number of points to transform.
 */
JNIEXPORT void JNICALL Java_org_proj4_PJ_transform
  (JNIEnv *env, jobject object, jobject target, jboolean hasZ, jdoubleArray coordinates, jint offset, jint numPts)
{
    if (!target || !coordinates) {
        jclass c = (*env)->FindClass(env, "java/lang/NullPointerException");
        if (c) (*env)->ThrowNew(env, c, "The target CRS and the coordinates array can not be null.");
        return;
    }
    int dimension = hasZ ? 3 : 2;
    if ((offset < 0) || (numPts < 0) || (offset + dimension*numPts) > (*env)->GetArrayLength(env, coordinates)) {
        jclass c = (*env)->FindClass(env, "java/lang/ArrayIndexOutOfBoundsException");
        if (c) (*env)->ThrowNew(env, c, "Illegal offset or illegal number of points.");
        return;
    }
    PJ *src_pj = getPJ(env, object);
    PJ *dst_pj = getPJ(env, target);
    if (src_pj && dst_pj) {
        // Using GetPrimitiveArrayCritical/ReleasePrimitiveArrayCritical rather than
        // GetDoubleArrayElements/ReleaseDoubleArrayElements increase the chances that
        // the JVM returns direct reference to its internal array without copying data.
        // However we must promise to run the "critical" code fast, to not make any
        // system call that may wait for the JVM and to not invoke any other JNI method.
        double *data = (*env)->GetPrimitiveArrayCritical(env, coordinates, NULL);
        if (data) {
            double *x = data + offset;
            double *y = x + 1;
            double *z = hasZ ? y+1 : NULL;
            int err = pj_transform(src_pj, dst_pj, numPts, dimension, x, y, z);
            (*env)->ReleasePrimitiveArrayCritical(env, coordinates, data, 0);
            if (err) {
                jclass c = (*env)->FindClass(env, "org/proj4/PJException");
                if (c) (*env)->ThrowNew(env, c, pj_strerrno(err));
            }
        }
    }
}

/*!
 * \brief
 * Deallocate the PJ structure. This method is invoked by the garbage collector exactly once.
 *
 * \param env - The JNI environment.
 * \param object - The Java object wrapping the PJ structure (not allowed to be NULL).
 */
JNIEXPORT void JNICALL Java_org_proj4_PJ_finalize
  (JNIEnv *env, jobject object)
{
    PJ *pj = getPJ(env, object);
    if (pj) pj_free(pj);
}

#endif
