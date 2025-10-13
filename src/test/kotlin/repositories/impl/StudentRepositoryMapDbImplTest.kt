package repositories.impl

import models.Student
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.test.BeforeTest
import kotlin.test.Test

class StudentRepositoryMapDbImplTest {

    private lateinit var map: MutableMap<Int, Student>
    private lateinit var repo: StudentRepositoryMapDbImpl

    @BeforeTest
    fun setUp() {
        map = mutableMapOf()
        repo = StudentRepositoryMapDbImpl(map)
    }

    @Test
    fun `create should assign id and store the student`() {
        val s = Student(legajo = "123", firstName = "Juan", lastName = "Perez", email = "juan@email.com")
        val created = repo.create(s)

        assertEquals(1, created.id)
        assertEquals(s.legajo, created.legajo)
    }

    @Test
    fun `getAll should return all students`() {
        val s1 = repo.create(Student(legajo = "aaa", firstName = "A", lastName = "B", email = "a@b"))
        val s2 = repo.create(Student(legajo = "bbb", firstName = "C", lastName = "D", email = "c@d"))
        val all = repo.getAll()

        assertEquals(2, all.size)
        assertTrue(all.containsAll(listOf(s1, s2)))
    }

    @Test
    fun `get should return correct student by id`() {
        val s = repo.create(Student(legajo = "999", firstName = "ZZ", lastName = "YY", email = "z@y"))
        val found = repo.get(s.id)
        assertEquals(s, found)
    }

    @Test
    fun `update should change existing student`() {
        val s = repo.create(Student(legajo = "x", firstName = "A", lastName = "B", email = "a@b"))
        val updated = repo.update(s.copy(firstName = "Nuevo"))
        assertNotNull(updated)
        assertEquals("Nuevo", updated?.firstName)
    }

    @Test
    fun `delete should remove student and return true`() {
        val s = repo.create(Student(legajo = "y", firstName = "B", lastName = "C", email = "b@c"))
        val deleted = repo.delete(s.id)
        assertTrue(deleted)
    }

    @Test
    fun `delete should return false if student does not exist`() {
        val deleted = repo.delete(999)
        assertFalse(deleted)
    }
}
