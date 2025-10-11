package routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import models.Student
import repositories.StudentRepository

fun Routing.studentRoutes() {
    route("/students") {
        get {
            call.respond(StudentRepository.getAll())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@get call.respond(HttpStatusCode.BadRequest)
            val student = StudentRepository.get(id)
            if (student != null)
                call.respond(student)
            else
                call.respond(HttpStatusCode.NotFound)
        }

        post {
            val studentData = call.receive<Student>()
            val student = StudentRepository.create(
                studentData.legajo,
                studentData.firstName,
                studentData.lastName,
                studentData.email
            )
            call.respond(HttpStatusCode.Created, student)
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(HttpStatusCode.BadRequest)
            val studentData = call.receive<Student>()
            val student = StudentRepository.update(
                id,
                studentData.legajo,
                studentData.firstName,
                studentData.lastName,
                studentData.email
            )
            if (student != null)
                call.respond(student)
            else
                call.respond(HttpStatusCode.NotFound)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respond(HttpStatusCode.BadRequest)
            val deleted = StudentRepository.delete(id)
            if (deleted)
                call.respond(HttpStatusCode.NoContent)
            else
                call.respond(HttpStatusCode.NotFound)
        }
    }
}
