package tech.blur.bluredu.common

sealed class Result<TValue, TError> {
    val isSuccess: Boolean get() = this !is Failure
    val isFailure: Boolean get() = this is Failure

    inline fun getOrElse(onFailure: (exception: TError) -> TValue): TValue {
        return when (val error = errorOrNull()) {
            null -> (this as Success).value
            else -> onFailure(error)
        }
    }

    fun errorOrNull(): TError? =
            when (this) {
                is Failure -> error
                else -> null
            }

    fun getOrDefault(defaultValue: TValue): TValue {
        return if (isFailure) defaultValue
        else (this as Success).value
    }

    fun onSuccess(onSuccessBlock: (TValue) -> Unit): Result<TValue, TError> {
        if (this is Success) onSuccessBlock(value)
        return this
    }

    fun onError(onErrorBlock: (TError) -> Unit): Result<TValue, TError> {
        if (this is Failure) onErrorBlock(error)
        return this
    }

    data class Success<T, R>(var value: T) : Result<T, R>()
    data class Failure<T, R>(val error: R) : Result<T, R>()

    companion object {
        fun <TValue, TError> success(value: TValue): Result<TValue, TError> = Success(value)

        fun <TValue, TError> failure(error: TError): Result<TValue, TError> = Failure(error)
    }
}

fun <TValue, TError, NValue> Result<TValue, TError>.mapSuccess(block: (value: TValue) -> NValue): Result<NValue, TError> {
    return when (this) {
        is Result.Success -> Result.success<NValue, TError>(block(value))
        is Result.Failure -> Result.failure<NValue, TError>(error)
    }
}

fun <TValue, TError> Result<TValue, TError>.mapSuccessToUnit(): Result<Unit, TError> {
    return mapSuccess { Unit }
}