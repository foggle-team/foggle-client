package org.foggle.client.exception

/**
 * Thrown when failing to contact Foggle backend properly
 */
class FoggleException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
