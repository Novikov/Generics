package kotlin_pecs

import java_pecs.Camera
import java_pecs.Phone
import java_pecs.Product
import java.util.*

    fun main(args: Array<String>) {
        val productsSrc: MutableList<Product> = ArrayList()
        productsSrc.add(Camera())
        productsSrc.add(Phone())
        productsSrc.add(Camera())
        val listDst: MutableList<Product> = ArrayList()
        val helper = Helper()
        helper.copy(productsSrc, listDst)
        for (p in listDst) {
            println(p.javaClass)
        }
    }


internal class Helper {
    fun copy(src: List<Product>, dest: MutableList<in Product>) {
        for (p in src) {
            dest.add(p)
        }
    }
}