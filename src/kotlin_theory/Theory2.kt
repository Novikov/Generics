package kotlin_theory2

//В данном файле встраиваются in и out параметры
fun main(args:Array<String>){
    val mutableInts = mutableListOf(1, 2, 3)
//    val mutableNumbers: MutableList<Number> = mutableInts
}

fun compare(comparator: Comparable<Number>) {
    val int: Int = 1
    comparator.compareTo(int)
    val float: Float = 1.0f
    comparator.compareTo(float)

    val intComparable: Comparable<Int> = comparator

    intComparable.compareTo(int)
//    intComparable.compareTo(float)
}
