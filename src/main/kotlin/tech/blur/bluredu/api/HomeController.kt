package tech.blur.bluredu.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import tech.blur.bluredu.core.BaseResponseEntity
import javax.servlet.http.HttpServletRequest


@Controller("HomeController")
class HomeController {

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