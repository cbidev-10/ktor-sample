import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStarted
import models.Student
import org.koin.ktor.ext.inject
import repositories.IStudentRepository

fun Application.configureWarmup() {
    val repository by inject<IStudentRepository>()

    environment.monitor.subscribe(ApplicationStarted) {
        if (repository.getAll().isEmpty()) {
            val initialStudents = listOf(
                Student(
                    legajo = "20321451170/15",
                    firstName = "Stephen",
                    lastName = "Curry",
                    email = "step.curry@test.com"
                ),
                Student(
                    legajo = "20121461170/15",
                    firstName = "Klay",
                    lastName = "Thompson",
                    email = "klay.thompson@test.com"
                ),
                Student(
                    legajo = "20921421871/15",
                    firstName = "Giannis",
                    lastName = "Antetokounmpo",
                    email = "giannis.anteto@test.com"
                ),
                Student(
                    legajo = "20222351672/15",
                    firstName = "Michael",
                    lastName = "Jordan",
                    email = "michael.jordan@test.com"
                ),Student(
                    legajo = "20526452196/15",
                    firstName = "LeBron",
                    lastName = "James",
                    email = "lebron.james@test.com"
                ),

            )
            initialStudents.forEach { repository.create(it) }
        }
    }
}
