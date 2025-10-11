import domain.StudentUseCases
import handlers.StudentHandler
import org.koin.dsl.module
import repositories.StudentRepository

val appModule = module {
    single {
        StudentRepository()
    }

    single {
        StudentUseCases(get())
    }

    single {
        StudentHandler(get())
    }
}
