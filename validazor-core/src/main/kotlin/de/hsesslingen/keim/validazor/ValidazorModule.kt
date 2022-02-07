package de.hsesslingen.keim.validazor

/**
 * A module that can be used to modify the behavior of [Validazor]s.
 *
 * A module can simply contain a collection of [ConstraintValidazor]s but may also
 * alter the properties of the [Validazor.Builder] instances that are given to it,
 * resulting in different behavior of [Validazor] instances, that are built using this [Validazor.Builder].
 *
 * The behavior of the module should be well documented and kept as concise and simple as possible.
 */
interface ValidazorModule {
    /**
     * This function will be called when registering this module in a [Validazor.Builder] with the instance
     * it was registered in.
     *
     * Use [builder] to configure the behavior of resulting validators as desired.
     * Keep in mind that changing general properties of the [builder] can result in side effects,
     * and that such changes can also be overridden by modules that are registered after this one.
     *
     * It is not forbidden to alter the general configuration of [builder] but it is discouraged to do so,
     * and if done should be well documented and changes should be reduced to a minimum.
     */
    fun configure(builder: Validazor.Builder)
}