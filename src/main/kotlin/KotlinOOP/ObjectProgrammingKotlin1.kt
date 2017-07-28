package main.kotlin.KotlinOOP

import java.awt.print.Printable
import java.io.InputStream
import java.io.OutputStream

/**
 * Created by stese on 25/07/2017.
 */

// In OOP:
// 1) Everything is an object, an object is a block of memory allocated and configured accordingly to design and intended behavior. From the problem space you need to solve
//you take everything you need and create objects from them
// 2) Objects communicate through sending and receiving messages ( in terms of objects) : The program will be a set of objects performing different actions calling methods
// that are implemented
// 3) Objects have their own memory: you can create objects by composing other objects
// 4) Every object is an instance of a class (which must be an object): Think of class as a blueprint of an object about what it can do
/*
* 5) Classes holds the shared behavior for all its instances: this means, all objects of a particular type can receive the same messages / expose the same methods
*
*
* Kotlin support all of this and also the three pillar of OOP, inheritance, encapsulation and polymorphism. Inheritance: create a new class from existing one
* Encapsulation: a group of related fields and methods are treated as an object
* Polymorphism: can use different classes interchangeably despite the fact that each one implements the same methods differently
*
* Also we need to provide the following, Simplicity, Modularity, Modifiability, Extensibility, Reusability
* Simplicity: Objects need to abstract the real world reducing complexity and streamlining the program structure
* Modularity: Each objects internal working mechanisms are decoupled from the other parts of the program
* Modifiability: Change inside an object doesn't affect other parts of the program
* Extensibility: Objects need to change fast if they model the real world and must respond quickly and efficiently to those changes
* Reusability: Objects can be used in other programs
*
*
*
* */


////////////////////Classes

//All objects despite being unique are part of a class and share common behaviour


class Deposit {

}

class Person (val firstName: String, val lastName: String, val age: Int?) {
    //Are those arguments created as public fields of the class? No they are created as properties
    init {
        //trim() cut whitespaces at the beginning and at the end of the string
        require( firstName.trim().length > 0) { "Invalid firstName argument"}
        require(lastName.trim().length > 0) {"Invalid lastName argument"}

        if (age != null) {
            require(age >= 0 && age < 150) { "Invalid age argument"}
        }
    }
    // A default constructor when we don't have the third parameter
    constructor(firstName: String, lastName: String) : this(firstName,lastName,null)
}

// Typical singleton design, having a private construcor and get it with getInstance()
// When defining abstract classes you should define the constructor visibility as protected

class secondPerson (firstName: String, lastName: String, howOld: Int? ) {

    private val name: String
    private val age: Int?

    init {
        this.name = "$firstName,$lastName"
        this.age = howOld
    }

    fun getName() : String = this.name
    fun getAge() : Int? = this.age

}

// Public, default visibility for Kotlin classes
//  Internal, create an instance in the module
// Protected, only valid for subclasses, not available for file-type level of declaration
// Private, accessible only in the scope of the file defining it

class Outer() {
    /*static*/ class StaticNested () {}
    class Inner() { }
    //Difference betwen Inner and StaticNested, Inner class can access the element of the Outer class even if they are private , static class can only access public members
    //Furthermore to create an instance of an Inner class you will need an instance of the Outer class first
}

// In Kotlin

//Static inner class
class BasicGraph(val name: String) {
    class Line(val x1: Int, val x2: Int, val y1: Int, val y2: Int ) { //This is the equivalent of a static nested class
        fun draw() {
            println("Drawing line from ($x1,$y1) to ($x2.$y2)")
        }
    }
    fun draw() {
        println("Drawing the graph $name")
    }
}

// Innter class, can access private properties/functions of the outerclass
class BasicGraphWithInner (name: String) {

    private val graphName: String
    init {
        graphName = name
    }

    inner class InnerLine(val x1: Int, val x2: Int, val y1:Int, val y2:Int) {
        fun draw() {
            println("Drawing line from ($x1,$y1) to ($x2,$y2) for graph $graphName")
        }
    }

}

// Data classes
data class Costumer(val id:Int, val name:String, var address: String) {
}

//You can refer specific instamces with the @ notation, like
class A() {
    private val someField : Int = 5
    inner class B () {
        private val someField :Int = 6
        fun foo() {
            println("Printing a field from B ${this.someField}" + this.someField)
            println("Printing a field from A ${this@A.someField}")
            println("Printing a field from B ${this@B.someField}")
        }
    }
}

//Enum classes

enum class Day {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }
enum class Planet (val mass : Double, val radius : Double) {
    MERCURY(3.303e+23, 2.4397e+6), VENUS(4.869e+24, 6.051e+6), EARTH(5.97e24, 6.37814e6)
}

// Enumeration can inherit from interfaces like other classes

interface Printable {
    fun print() : Unit {}
}

enum class Word : main.kotlin.KotlinOOP.Printable {

    HELLO {
        override fun print() {
            println("Word is HELLO")
        }
    },
    WORLD {
        override fun print() {
            println("Word is WORLD")
            super.print()
        }
    },
/*    I,
    WILL,
    GET,
    THIS,
    DEGREE*/

}


// Static methods and companion objects

// Static methods doesn't belong to the object but to the class itself, Kotlin doesn't support those!
// it is advisable to define methods at package level to achieve this functionality
// We can define a new kotlin file and name it Static so that the function inside it works as Static Functions

//Singletons : Limit the instantiation of a given class to a single object, once created it will live through the program

object Singleton {
    private var count = 0
    fun doSomething() : Unit {
        println("Calling a  doSomething, ${++count} call /s in total ")
    }
}


// Singletons can inherit from other classes

open class SingletonParent (var x: Int) {
    fun something(): Unit {
        print("X = $x")
    }
}

object SingletonChild : SingletonParent(10)

// Companion Object, a way to to create a static method like in java, place the object withing a class and call it companion object,

interface StudentFactory {
    fun create (name : String) : Student
}


//Factory design pattern to create a student object
class Student private constructor(val name: String) {
    companion object : StudentFactory {
        override fun create(name: String): Student {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.ù
            return Student(name)
        }
    }
}

//Only the companion object can access the method of Student


// Interfaces

interface Document {
    val version : Long
    val size : Long

    val name : String
    get() = "NoName"

    fun save(input: InputStream)
    fun load(stream: OutputStream)
    fun getDescription () : String {
        return "Document $name has size $size byte(-s) "
    }
}

// In java, when you implement an interface you transform the properties into getters , despite providing default implementations for getDescription along with the name
// you still have to implement them
// In kotlin is not the case

class DocumentImpl :  Document {
    override val size: Long
        get() = 0//To change initializer of created properties use File | Settings | File Templates.
    override val version: Long
        get() = 0 //To change initializer of created properties use File | Settings | File Templates.

    override fun load(stream: OutputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun save(input: InputStream) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDescription(): String {
        return super.getDescription()
    }
}


fun main(args: Array<String>) {
    val person1 = Person("Alex", "Minnes", 52)
    val person2 = Person("Becky", "Moore") //second constructor default age to null
    println("${person1.firstName} is ${person1.age} years old")
    println("${person2.firstName} is ${person2.age?.toString() ?: "? don't know" } years old")
    // If null print don't know , but age must be set to age? if we want the elvis operator to work

    val person3 = secondPerson("John", "Bluebin", 40)
    person3.getName() // now we have only these methods

    val line = BasicGraph.Line(1,1,2,3)
    line.draw()

    println(Planet.values())
    println(Planet.valueOf("MERCURY").mass)
    val monday = Day.MONDAY
    println(monday)

    //Like a Static method
    firstCharacterOfString("Kotlin is Cool")

    Singleton.doSomething()

    val lol = Singleton // is not new, is already instantiated
    val lol2 = Singleton //also this is not a new singleton, is always the same, just lol2 now reference it

    println("${lol2 === lol }") // they reference the same address in memory

    lol.doSomething()
    lol2.doSomething()

    Student.Companion.create("Giacomo ") // a companion object follow all the inheritance rules

    // INTERFACES pag 69
}