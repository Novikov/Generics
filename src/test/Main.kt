package test

fun main(){
    val list = listOf<Any>("A",1,false)
    printList(list)
}

fun printList(item:List<*>){
    item.forEach {
        println(it.toString())
    }
}