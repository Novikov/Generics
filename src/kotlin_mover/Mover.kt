package kotlin_mover

interface Checkable {
    fun checkIsOK(): Boolean
}

class Mover<T:Checkable>(thingsToMove: List<T>, val truckHeightInInches: Int = (12 * 12)) {

    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<T>()
    private var thingsInNewPlace = mutableListOf<T>()
    private var thingsWhichFailedCheck = mutableListOf<T>()

    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }

    fun moveEverythingToTruck() {
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)
            if (item.checkIsOK()) {
                thingsInTruck.add(item)
                println("Moved your $item to the truck!")
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck :[")
            }
        }
    }



    fun moveEverythingIntoNewPlace() {
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0)
            if (item.checkIsOK()) {
                thingsInNewPlace.add(item)
                println("Moved your $item into your new place!")
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item into your new place :[")
            }
        }
    }

    fun finishMove() {
        println("OK, we finished! We were able to move your:${thingsInNewPlace.toBulletedList()}")
                if (thingsWhichFailedCheck.isNotEmpty()) {
                    println("But we need to talk about your:${thingsWhichFailedCheck.toBulletedList()}")
                }
    }
}

class CheapThing(val name: String) : Checkable {
    override fun checkIsOK(): Boolean {
        return true
    }

    override fun toString(): String {
        return name
    }
}

class BreakableThing(
        val name: String,
        var isBroken: Boolean = false
) : Checkable{
    fun smash() {
        isBroken = true
    }

    override fun checkIsOK(): Boolean {
        return !isBroken
    }

    override fun toString(): String {
        return name
    }
}

