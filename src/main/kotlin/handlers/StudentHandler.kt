package handlers

import domain.StudentUseCases
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import models.Student


class StudentHandler(
    private val studentUseCases: StudentUseCases,
) {
    fun Route.studentRoutes() {
        route("/students") {

            get("/") {
                val students = studentUseCases.getAllStudents()
                call.respond(students)
            }

            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) return@get call.respond(HttpStatusCode.BadRequest)
                val student = studentUseCases.getStudentById(id)
                if (student != null)
                    call.respond(student)
                else
                    call.respond(HttpStatusCode.NotFound)
            }

            post {
                val student = call.receive<Student>()
                val newStudent = studentUseCases.createStudent(student)
                call.respond(HttpStatusCode.Created, newStudent)
            }
        }
    }
}
