package tech.blur.bluredu.errors

class AuthenticationError(override val message: String? = "Authentication error: Wrong password") : Exception()