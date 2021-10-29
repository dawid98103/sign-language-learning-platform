package pl.dkobylarz.signlearning.domain.forum.domain

import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.user.domain.UserPlatform
import java.time.LocalDateTime

class ForumMapper {

    companion object {
        fun mapToPost(postToCreate: CreatePostDTO, userId: Int): Post {
            return Post(
                0,
                postToCreate.topic,
                postToCreate.content,
                LocalDateTime.now(),
                LocalDateTime.now(),
                userId,
                mutableSetOf()
            )
        }

        fun mapToComment(commentDTO: CreateCommentDTO, userId: Int): Comment {
            return Comment(
                0,
                commentDTO.content,
                LocalDateTime.now(),
                LocalDateTime.now(),
                userId
            )
        }

        fun mapToPostDTO(post: Post, userPlatform: UserPlatform): PostDTO {
            val simplePostDTO: SimplePostDTO = mapToSimplePost(post, userPlatform)
            val commentsDTO: Set<CommentDTO> = post.comments.asSequence()
                .map { mapToCommentDTO(it, userPlatform) }
                .toSet()
            return PostDTO(
                simplePostDTO,
                commentsDTO
            )
        }

        fun mapToCommentDTO(comment: Comment, userPlatform: UserPlatform): CommentDTO {
            return CommentDTO(
                comment.commentId,
                comment.content,
                comment.creationDate,
                userPlatform.avatarUrl,
                userPlatform.username
            )
        }

        fun mapToSimplePost(post: Post, userPlatform: UserPlatform): SimplePostDTO {
            return SimplePostDTO(
                post.postId,
                post.topic,
                post.content,
                userPlatform.username,
                userPlatform.avatarUrl,
                post.creationDate
            )
        }
    }
}