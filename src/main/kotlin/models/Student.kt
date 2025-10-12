package models

import java.io.Serializable

data class Student(
    val id: Int = 0,
    val legajo: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
) : Serializable