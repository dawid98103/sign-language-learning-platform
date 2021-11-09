package pl.dkobylarz.signlearning.domain.user.dto

data class UserBasicInfoWithFriendListDTO(
    val userBasicInfoDTO: UserBasicInfoDTO,
    val friends: Set<UserBasicInfoDTO>,
    val currentUserAccount: Boolean
)
