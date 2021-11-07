package pl.dkobylarz.signlearning.domain.forum.domain

import pl.dkobylarz.signlearning.domain.forum.dto.*
import pl.dkobylarz.signlearning.domain.postlike.dto.PostLikeDTO
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

        fun mapToPost(simplePostDTO: SimplePostDTO, post: Post): Post {
            return Post(
                post.postId,
                post.topic,
                simplePostDTO.content,
                post.creationDate,
                LocalDateTime.now(),
                post.userId,
                post.comments
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

        fun mapToComment(commentDTO: CommentDTO, comment: Comment): Comment {
            return Comment(
                comment.commentId,
                commentDTO.content,
                comment.creationDate,
                LocalDateTime.now(),
                comment.userId
            )
        }

        fun mapToPostDTO(
            post: Post,
            userPlatform: UserPlatform,
            currentlyLoggedUser: User,
            postLikes: Set<PostLikeDTO>
        ): PostDTO {
            val simplePostDTO: SimplePostDTO = mapToSimplePost(post, userPlatform, currentlyLoggedUser, postLikes)
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

        fun mapToSimplePost(
            post: Post,
            userPlatform: UserPlatform,
            currentlyLoggedUser: User,
            postLikes: Set<PostLikeDTO>
        ): SimplePostDTO {
            return SimplePostDTO(
                post.postId,
                post.topic,
                post.content,
                userPlatform.username,
                userPlatform.avatarUrl,
                post.creationDate,
                userPlatform.userId == currentlyLoggedUser.userId,
                postLikes
            )
        }
    }
}