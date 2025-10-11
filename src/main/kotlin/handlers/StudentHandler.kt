package handlers

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import mappers.toResponseDto
import models.dto.StudentRequestDto
import usecases.StudentUseCases

class StudentHandler(
    private val studentUseCases: StudentUseCases,
) {
    fun Route.studentRoutes() {
        route("/students") {

            get("/") {
                val students = studentUseCases.getAllStudents()
                call.respond(students.map { it.toResponseDto() })
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
                val student = studentUseCases.getStudentById(id)
                if (student != null)
                    call.respond(student.toResponseDto())
                else
                    call.respond(HttpStatusCode.NotFound)
            }

            post {
                val student = call.receive<StudentRequestDto>()
                val newStudent = studentUseCases.createStudent(student)
                call.respond(HttpStatusCode.Created, newStudent.toResponseDto())
            }
        }
    }
}
