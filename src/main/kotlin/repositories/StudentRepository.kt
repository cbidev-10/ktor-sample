package repositories

import domain.Student

class StudentRepository {
    private val students = mutableListOf(
        Student(id = 1, legajo = "92838219/25", "Israel", "Adesanya", "yzzy@gmail.com"),
        Student(id = 2, legajo = "81256711/25", "Dricus", "Du Plessis", "dri@gmail.com")
    )
    private var lastId = 0

    fun getAll(): List<Student> = students

    fun get(id: Int): Student? = students.find { it.id == id }

    fun create(legajo: String, firstName: String, lastName: String, email: String): Student {
        val student = Student(++lastId, legajo, firstName, lastName, email)
        students.add(student)
        return student
    }

    fun update(id: Int, legajo: String?, firstName: String?, lastName: String?, email: String?): Student? {
        val student = get(id) ?: return null
        val index = students.indexOf(student)
        val updated = student.copy(
            legajo = legajo ?: student.legajo,
            firstName = firstName ?: student.firstName,
            lastName = lastName ?: student.lastName,
            email = email ?: student.email
        )
        students[index] = updated
        return updated
    }

    fun delete(id: Int): Boolean = students.removeIf { it.id == id }
}
