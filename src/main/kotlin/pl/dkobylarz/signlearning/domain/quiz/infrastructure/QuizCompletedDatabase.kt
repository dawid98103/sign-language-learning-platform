package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import pl.dkobylarz.signlearning.domain.quiz.domain.QuizCompleted

interface QuizCompletedDatabase {
    fun save(quizCompleted: QuizCompleted)
}