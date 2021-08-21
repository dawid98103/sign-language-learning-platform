package pl.dkobylarz.signlearning.application

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
class ErrorHandlerController : ErrorController {

    @RequestMapping("/error")
    fun errorHandling(): ModelAndView {
        return ModelAndView("index")
    }
}