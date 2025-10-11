import domain.StudentUseCases
import handlers.StudentHandler
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import repositories.StudentRepository

fun Application.module() {
    plugins()

    val repository = StudentRepository
    val studentUseCases = StudentUseCases(repository)
    val handler = StudentHandler(studentUseCases)

    routing {
        with(handler) { studentRoutes() }
    }
}