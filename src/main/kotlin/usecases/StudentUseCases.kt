package usecases

import models.Student
import repositories.IStudentRepository

class StudentUseCases(private val studentRepository: IStudentRepository) {

    fun getAllStudents(): List<Student> {
        return studentRepository.getAll()
    }

    fun getStudentById(id: Int): Student? {
        return studentRepository.get(id)
    }

    fun createStudent(student: Student): Student {
        return studentRepository.create(student)
    }

    fun updateStudent(student: Student): Student? {
        return studentRepository.update(student)
    }

    fun deleteStudent(id: Int): Boolean {
        return studentRepository.delete(id)
    }
}