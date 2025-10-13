package repositories

import models.Student

interface IStudentRepository {
    fun getAll(): List<Student>
    fun get(id: Int): Student?
    fun create(student: Student): Student
    fun update(student: Student): Student?
    fun delete(id: Int): Boolean
}
