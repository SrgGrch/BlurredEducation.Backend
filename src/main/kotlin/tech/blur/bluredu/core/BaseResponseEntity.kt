package tech.blur.bluredu.core

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * Create a new {@code ResponseEntity} with the given body and status code, and no headers.
 * @param body the entity body
 * @param status the status code
 * @param message response message
 */
class BaseResponseEntity<T>(body: T, status: HttpStatus, val message: String = "") : ResponseEntity<T>(body, status) {


    /**
     * OK status default constructor
     * @param T body type
     * @param body response body
     */
    constructor(body: T) : this(body, HttpStatus.OK)


    /**
     *  OK status default constructor with message
     *
     *  @param T body type
     *
     *  @param body response body
     *  @param message message
     */
    constructor(body: T, message: String) : this(body, HttpStatus.OK, message)
}