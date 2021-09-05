package pl.dkobylarz.signlearning.domain.quiz.domain

import org.springframework.data.relational.core.mapping.Table

@Table("quiz")
data class Quiz(
    private val quizId: Int,
    private val title: String,
    private val pointsToGain: Int
)