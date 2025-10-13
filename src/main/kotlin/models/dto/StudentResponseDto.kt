package models.dto

import models.Student

data class StudentResponseDto(
    val id: Int,
    val legajo: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun Student.toResponseDto() = StudentResponseDto(
    id = id,
    legajo = legajo,
    firstName = firstName,
    lastName = lastName,
    email = email
)
