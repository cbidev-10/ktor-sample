import handlers.StudentHandler
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.registerRoutes() {
    val studentHandler: StudentHandler by inject()

    routing {
        with(studentHandler) {
            studentRoutes()
        }
    }
}