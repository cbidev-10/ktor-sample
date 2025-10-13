package repositories.impl

import models.Student
import repositories.IStudentRepository

class StudentRepositoryMapDbImpl(
    private val map: MutableMap<Int, Student>,
) : IStudentRepository {
    private var lastId = 0

    override fun getAll(): List<Student> =
        map.values.toList()

    override fun get(id: Int): Student? =
        map[id]

    override fun create(student: Student): Student {
        val s = student.copy(id = ++lastId)
        map[s.id] = s
        return s
    }

    override fun update(student: Student): Student? {
        if (!map.containsKey(student.id)) return null
        map[student.id] = student
        return student
    }

    override fun delete(id: Int): Boolean =
        map.remove(id) != null
}
