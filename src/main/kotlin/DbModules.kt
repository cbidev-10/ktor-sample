import models.Student
import org.koin.dsl.module
import org.mapdb.DB
import org.mapdb.DBMaker
import org.mapdb.Serializer

val mapDbModule = module {
    single<DB> {
        DBMaker.memoryDB().make()
    }

    single<MutableMap<Int, Student>> {
        get<DB>().hashMap(
            "students",
            Serializer.INTEGER, Serializer.JAVA
        ).createOrOpen() as MutableMap<Int, Student>
    }
}