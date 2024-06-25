package sm.utils

import com.fasterxml.jackson.annotation.JsonInclude

class ResultWrapper<T>(
    var code: Int = 1,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var message: String? = null,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T? = null
){
    companion object {
        fun ok() : ResultWrapper<Unit> = ResultWrapper(code.S_OK, null, null)
        fun fail(msg: String? = "") : ResultWrapper<Unit> = ResultWrapper(code.S_FAIL, msg, null)
        fun error(msg: String?) : ResultWrapper<Unit> = ResultWrapper(code.ERROR, msg, null)

        fun <T> ok(data: T?) : ResultWrapper<T> = ResultWrapper(code.S_OK, null, data)
        fun <T> fail(msg: String, data: T?) : ResultWrapper<T> = ResultWrapper(code.S_FAIL, msg, data)
        fun <T> error(msg: String, data: T?) : ResultWrapper<T> = ResultWrapper(code.ERROR, msg, data)

        fun <T> of(st: Int, msg: String? = null, data: T? = null) : ResultWrapper<T> = ResultWrapper(st, msg, data)
    }
}