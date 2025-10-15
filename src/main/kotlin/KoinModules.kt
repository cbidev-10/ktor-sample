import handlers.StudentHandler
import org.koin.dsl.module
import repositories.IStudentRepository
import repositories.impl.StudentRepositoryMapDbImpl
import usecases.StudentUseCases

val appModule = module {
    single<IStudentRepository> {
        StudentRepositoryMapDbImpl(get())
    }

    single {
        StudentUseCases(get())
    }

    single {
        StudentHandler(get())
    }
}
