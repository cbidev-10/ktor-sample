package domain

import models.Student
import repositories.StudentRepository

class StudentUseCases(val studentRepository: StudentRepository) {

    fun getAllStudents(): List<Student> {
        return studentRepository.getAll()
    }

    fun getStudentById(id: Int): Student? {
        return studentRepository.get(id)
    }

    fun createStudent(student: Student): Student {
        // TODO: Este es el punto en el que hay que validar ciertos valores
        return studentRepository.create(student.legajo, student.firstName, student.lastName, student.email)
    }

    fun updateStudent(student: Student): Student? {
        // TODO: Acá tambien deberíamos chequear ciertas cosas
        return studentRepository.update(student.id, student.legajo, student.firstName, student.lastName, student.email)
    }

    fun deleteStudent(id: Int) {
        // TODO: Acá chequear la decisión, si es importante retornar que esta ok o no.
        studentRepository.delete(id)
    }
}