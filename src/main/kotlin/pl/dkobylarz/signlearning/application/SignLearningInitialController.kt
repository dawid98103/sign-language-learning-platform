package pl.dkobylarz.signlearning.application

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class SignLearningInitialController {

    @GetMapping("")
    fun getAppRoot(): ResponseEntity<String> {
        return ResponseEntity.ok("SignLearning REST API v.1.0.0");
    }
}