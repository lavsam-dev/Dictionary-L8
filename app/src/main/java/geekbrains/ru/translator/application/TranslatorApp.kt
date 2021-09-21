package geekbrains.ru.translator.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryL8 : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@DictionaryL8) }
    }
}
