package pl.dkobylarz.signlearning.domain.forum.domain

import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.user.domain.User
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
            return PostDTO(
                    simplePostDTO,
                    emptySet()
            )
        }

        fun mapToCommentDTO(comment: Comment, userForComment: UserPlatform, isLoggedUserAuthor: Boolean): CommentDTO {
            return CommentDTO(
                    comment.commentId,
                    comment.content,
                    comment.creationDate,
                    userForComment.avatarUrl,
                    userForComment.username,
                    isLoggedUserAuthor
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