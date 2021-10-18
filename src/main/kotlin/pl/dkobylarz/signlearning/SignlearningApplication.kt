package pl.dkobylarz.signlearning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.transaction.annotation.EnableTransactionManagement

@SpringBootApplication
@EnableTransactionManagement
class SignlearningApplication

fun main(args: Array<String>) {
    runApplication<SignlearningApplication>(*args)
}
