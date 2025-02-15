package org.example

fun main() {
    //read only variable val

    val x = true
    println("I can also explicitly define varible by, val x : boolean = true")
    println(x);
    println("however reassigning x to false will cause error, because val is read-only");

    var ourFirstVariable = false
    println(ourFirstVariable)
    ourFirstVariable = true
    println(ourFirstVariable)
    println("Here the reassigning doesn't cause error because of use of var instead of var")

    println(ourFirstVariable == true)
    println(ourFirstVariable == false)

}