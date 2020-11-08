package kotlin_mover

fun main() {
    val cheapThings = listOf(
            CheapThing("Cinder Block table"),
            CheapThing("Box of old books"),
            CheapThing("Ugly old couch")
    )
    val cheapMover = Mover(cheapThings)

    cheapMover.moveEverythingToTruck()
    cheapMover.moveEverythingIntoNewPlace()
    cheapMover.finishMove()

    val television = BreakableThing("Flat-Screen Television")
    val breakableThings = listOf(
            television,
            BreakableThing("Mirror"),
            BreakableThing("Guitar")
    )
    val expensiveMover = Mover(breakableThings)

    expensiveMover.moveEverythingToTruck()
    television.smash()
    expensiveMover.moveEverythingIntoNewPlace()
    expensiveMover.finishMove()

}

fun <T> List<T>.toBulletedList(): String {
    val separator = "\n - "
    return this.map { "$it" }.joinToString(separator, prefix = separator, postfix = "\n")
}

