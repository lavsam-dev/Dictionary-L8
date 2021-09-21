package geekbrains.ru.translator.view

import android.util.Log

interface Icar {
    fun drive()
}

interface IBoat {
    fun swim()
}

interface IDuck {
    fun krya()

    fun swim()
}

open class Car : Icar {
    override fun drive() {
        // car code
    }
}

open class Boat : IBoat {
    override fun swim() {
        // boat code
    }
}

open class Duck : IDuck {
    override fun krya() {
        // krya code
    }

    override fun swim() {
        // swim code
    }
}

class Monster(
    private val car: Car,
    private val boat: Boat,
    private val duck: Duck
) : Icar by car, IBoat by boat, IDuck {

//    override fun drive() {
//        // car code
//        car.drive()
//    }

    override fun krya() {
        // krya code
        Log.d("Monster", "krya krya")
        duck.krya()
    }

//    override fun swim() {
//        // boat code
//        duck.swim()
//    }
}

fun main() {
    val car = Car()
    val boat = Boat()
    val duck = Duck()
    val monster = Monster(car, boat, duck)

    monster.drive()
    monster.krya()
    monster.swim()
}