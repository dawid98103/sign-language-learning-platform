package pl.dkobylarz.signlearning.application

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

@Controller
@RequestMapping("/")
class SignLearningInitialController {

    @GetMapping("")
    fun getAppRoot(): ModelAndView {
        return ModelAndView("index")
    }
}