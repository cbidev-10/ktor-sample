package models.dto

import models.Student

data class StudentRequestDto(
    val legajo: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

fun StudentRequestDto.toStudent(id: Int = 0) = Student(
    id = id,
    legajo = legajo,
    firstName = firstName,
    lastName = lastName,
    email = email
)
