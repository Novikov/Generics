package kotlin_theory1

fun main(args:Array<String>){
    val cheapThings = listOf(
            CheapThing("Cinder Block table"),
            CheapThing("Box of old books"),
            CheapThing("Ugly old couch")
    )
    val cheapMover = Mover(cheapThings)

    cheapMover.moveEverythingToTruck(null)
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-Screen Television")
    val breakableThings = listOf(
            television,
            BreakableThing("Mirror"),
            BreakableThing("Guitar")
    )
    val expensiveMover = Mover(breakableThings)
    television.smash()
    expensiveMover.moveEverythingToTruck(CardboardBox())
    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()
}

fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix =
    separator, postfix = "\n")
}

class CheapThing(val name: String) : Checkable{
    override fun checkIsOK(): Boolean = true

    override fun toString(): String {
        return name
    }
}

class BreakableThing(val name: String, var isBroken: Boolean = false) : Checkable{
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

interface Checkable {
    fun checkIsOK(): Boolean
}

interface Container<T> {
    fun canAddAnotherItem(): Boolean
    fun addItem(item: T)
    fun canRemoveAnotherItem(): Boolean
    fun removeItem(): T
    fun getAnother(): Container<T>
    fun contents(): List<T>
}

class CardboardBox: Container<BreakableThing> {
    private var items = mutableListOf<BreakableThing>()

    override fun contents(): List<BreakableThing> {
        return items.toList()
    }

    override fun canAddAnotherItem(): Boolean {
        return items.count() < 2
    }

    override fun addItem(item: BreakableThing) {
        items.add(item)
    }

    override fun canRemoveAnotherItem(): Boolean {
        return items.count() > 0
    }

    override fun removeItem(): BreakableThing {
        val lastItem = items.last()
        items.remove(lastItem)
        return lastItem
    }

    override fun getAnother(): Container<BreakableThing> {
        return CardboardBox()
    }
}


class Mover<T: Checkable>(thingsToMove: List<T>, val truckHeightInInches: Int = (12 * 12)) {
    private var thingsLeftInOldPlace = mutableListOf<T>()
    private var thingsInTruck = mutableListOf<Any>()
    private var thingsInNewPlace = mutableListOf<T>()
    private var thingsWhichFailedCheck = mutableListOf<T>()

    init {
        thingsLeftInOldPlace.addAll(thingsToMove)
    }

    private fun moveContainerToTruck(container: Container<T>) {
        thingsInTruck.add(container)
        println("Moved a container with your ${container.contents().toBulletedList()} to the truck!")
    }

    private fun tryToMoveItemIntoNewPlace(item: T) {
        if (item.checkIsOK()) {
            thingsInNewPlace.add(item)
            println("Moved your $item into your new place!")
        } else {
            thingsWhichFailedCheck.add(item)
            println("Could not move your $item into your new place :[")
        }
    }

    //Данный метод можем вызвать с контейнером или без
    fun moveEverythingToTruck(startingContainer: Container<T>?) {
        var currentContainer = startingContainer
        while (thingsLeftInOldPlace.count() > 0) {
            val item = thingsLeftInOldPlace.removeAt(0)

            if (item.checkIsOK()) {
                if (currentContainer != null) {
                    if (!currentContainer.canAddAnotherItem()) {
                        moveContainerToTruck(currentContainer)
                        currentContainer = currentContainer.getAnother()
                    }
                    currentContainer.addItem(item)
                    println("Packed your $item!")
                } else {
                    thingsInTruck.add(item)
                    println("Moved your $item to the truck!")
                }
            } else {
                thingsWhichFailedCheck.add(item)
                println("Could not move your $item to the truck :[")
            }
            currentContainer?.let { moveContainerToTruck(it)}
        }
    }


    fun moveEverythingIntoNewPlace() {
        val containers = thingsInTruck.filterIsInstance<Container<T>>()

        //сначала из грузовика вытаскиваем контейнеры и распаковываем их в новое место
        for (container in containers) {
            thingsInTruck.remove(container)
            while (container.canRemoveAnotherItem()) {
                val itemInContainer = container.removeItem()
                println("Unpacked your $itemInContainer!")
                tryToMoveItemIntoNewPlace(itemInContainer)
            }
        }

        //затем выгружаем вещи без контейнеров
        while (thingsInTruck.count() > 0) {
            val item = thingsInTruck.removeAt(0) as? T
            if (item != null) {
                tryToMoveItemIntoNewPlace(item)
            } else {
                println("Something in the truck was not of the expected generic type: $item")
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