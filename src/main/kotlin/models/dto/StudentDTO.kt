package models.dto

data class StudentRequestDto(
    val legajo: String,
    val firstName: String,
    val lastName: String,
    val email: String
)

data class StudentResponseDto(
    val id: Int,
    val legajo: String,
    val firstName: String,
    val lastName: String,
    val email: String
)
