package tech.blur.bluredu.api

import io.swagger.annotations.Api
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import tech.blur.bluredu.core.BaseResponseEntity
import javax.servlet.http.HttpServletRequest


@Api(tags = ["Home"])
@RestController("HomeController")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class HomeController {

    @ResponseBody
    @RequestMapping("/index")
    fun hello(request: HttpServletRequest): BaseResponseEntity<String> {
        println(request.servletPath)
        return when ((1..100).random()) {
            in 1..80 -> BaseResponseEntity("Status: OK")
            in 81..90 -> BaseResponseEntity("Status: NULL")
            else -> BaseResponseEntity("Status: False")
        }
    }

}