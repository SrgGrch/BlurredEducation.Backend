package tech.blur.bluredu.core.exceptions

class AuthenticationError(override val message: String? = "Authentication error: Wrong password") : Exception()