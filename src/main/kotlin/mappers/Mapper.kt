package mappers

import domain.Student
import models.dto.StudentResponseDto

fun Student.toResponseDto() = StudentResponseDto(
    id = id,
    legajo = legajo,
    firstName = firstName,
    lastName = lastName,
    email = email
)
