package pl.dkobylarz.signlearning.domain.userlogging.domain

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class UserLoggingLogFacade(private val userLoginLogService: UserLoginLogService) {}