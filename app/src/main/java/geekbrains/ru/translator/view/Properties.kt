package geekbrains.ru.translator.view

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.reflect.KProperty

class Example {
    private var localName = ""
    var name: String
        get() = "123"
        set(value) {
            Log.d("Example", "Set name $localName = $value")
            localName = value
        }

    var surname: String = "Surname"
}

class Properties : AppCompatActivity() {

    private val propertyDelegate = PropertyDelegate<Properties>()


    var name by PropertyDelegate()

    val vm by viewModel<ViewModel>()
}

class PropertyDelegate<T> {

    private var holder: T? = null

    operator fun getValue(properties: T, property: KProperty<*>): Any {
        Log.d("PropertyDelegate", "Get value = $holder")
        return holder as Any
    }

    operator fun setValue(properties: T, property: KProperty<*>, any: Any) {
        Log.d("PropertyDelegate", "Set value $holder = $any")
        holder = any as T
    }
}

fun main() {
    val example = Example()

    example.surname = "321"
    print(example.surname)

    val p = Properties()

    p.name = "123"
    print(p.name)
}