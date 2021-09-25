package pl.dkobylarz.signlearning.domain.quiz.infrastructure

import org.springframework.data.repository.CrudRepository
import pl.dkobylarz.signlearning.domain.quiz.domain.QuizCompleted

interface QuizCompletedRepository : CrudRepository<QuizCompleted, Int>