package pl.sylwekczmil.ma.shared

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Cannot change other user data")
class OtherUserDataAccessException : RuntimeException()