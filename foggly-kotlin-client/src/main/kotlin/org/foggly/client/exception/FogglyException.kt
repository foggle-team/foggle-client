package org.foggly.client.exception

/**
 * Thrown when failing to contact Foggly backend properly
 */
class FogglyException : RuntimeException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
}
