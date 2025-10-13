package usecases

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import models.Student
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import repositories.IStudentRepository
import kotlin.test.Test

class StudentUseCasesMockTest {

    private val repo = mockk<IStudentRepository>(relaxed = true)
    private val useCases = StudentUseCases(repo)

    @Test
    fun `createStudent should call repo create and return new student`() {
        val fakeStudent = Student(legajo = "123", firstName = "Juan", lastName = "Perez", email = "jp@a.com")
        coEvery { repo.create(fakeStudent) } returns fakeStudent.copy(id = 42)

        val result = useCases.createStudent(fakeStudent)

        assertEquals(42, result.id)

        coVerify(exactly = 1) {
            repo.create(fakeStudent)
        }
    }

    @Test
    fun `getAllStudents should call repo getAll and return students`() {
        val list = listOf(
            Student(1, "111", "A", "B", "a@b.com"),
            Student(2, "222", "C", "D", "c@d.com")
        )
        coEvery { repo.getAll() } returns list

        val all = useCases.getAllStudents()
        assertEquals(2, all.size)
        assertEquals("A", all[0].firstName)

        coVerify { repo.getAll() }
    }

    @Test
    fun `getStudentById should call repo get and return correct student`() {
        val s = Student(1, "444", "X", "Y", "x@y.com")
        coEvery { repo.get(1) } returns s

        val result = useCases.getStudentById(1)
        assertNotNull(result)
        assertEquals("X", result?.firstName)

        coVerify { repo.get(1) }
    }

    @Test
    fun `updateStudent should call repo update and return updated student`() {
        val s = Student(1, "555", "Z", "Y", "z@y.com")
        coEvery { repo.update(s) } returns s.copy(firstName = "modify")

        val updated = useCases.updateStudent(s)
        assertEquals("modify", updated?.firstName)

        coVerify { repo.update(s) }
    }

    @Test
    fun `deleteStudent should call repo delete and return true`() {
        coEvery { repo.delete(1) } returns true

        val deleted = useCases.deleteStudent(1)
        assertTrue(deleted)

        coVerify { repo.delete(1) }
    }
}
