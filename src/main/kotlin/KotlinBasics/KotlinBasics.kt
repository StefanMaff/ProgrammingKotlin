package main.kotlin.KotlinBasics

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

/**
 * Created by stese on 17/07/2017.
 */

fun main(args: Array<String>) {

    val name = "kotlin"
    var newName = "more kotlin"
    newName = "kotlin's great"

    fun plusOneInteger (x: Int) = x+1 //one line functions with return type inferred from operation

    //sometimes we want to annotate the type

    val explicitType : Number = 12.3

    //In kotlin there aren't primitive types, everything is an object, while in java there are some types that are threated
    //differently from objects : cannot be used as generic types, cannot invoke methods and functions and cannot be null

    //Int,Float,Double,Long,Exadecimal,Binary

    val int = 123
    val long = 12345L
    val double = 125.986
    val float = 124.3F
    val binary = 0b010101000101 //requires prefix 0b
    val exa = 0xAAAA //requires prefix 0x

    //We can widen the number invoking the specific method

    val long2 = int.toLong()
    val double2 = float.toDouble()

    //bitwise operation let's see if we'll need them lol

    //Booleans are basically supported, with negation, conjunction OR and disjunction OR, lazily evaluated
    //if the left hand size satisfies the clause, the rest is not evaluated

    val isTrue = true
    val isFalse = false
    val isAlsoTrue = isTrue || isFalse
    assert(isAlsoTrue == true)

    println("$isAlsoTrue")

    //Chars, are single literals, they support escaping with \ like \t \b \" \$ \n etc
    val c = 'c'
    val escapedCharacter = '\n'
    val slashN = """\n""" //no escaping in raw strings

    //IMPORTANT: CHARS ARE NOT TREATED AS NUMBERS AS IN JAVA

    println(" Buongiorno $escapedCharacter $slashN   Buongiorno ")

    // STRINGS

    //strings in kotlin are also immutable as in java, i.e. can't reassign a value to a string , you can create
    //escaped strings with double quotes, or raw/unescaped strings with triple quotes

    val string = "hello boys \n \n hello girls"
    val rawString = """hello boys \n \n hello girls, today we are going to learn something fun"""

    //ARRAYS

    //arrays in kotlin are not treated as special and are a regular collection of classes
    //created using arrayOf
    val myArray = arrayOf(1,2,3)
    val myNewArray = arrayOf("Hello", "to", "everybody")

    //They can be created also with a special initiator function used to generate each element

    val arrayFromFunction = Array (10, {k -> k * k}) //k automatically goes from 0 to 9 ?

    for (i in 0..9) println("arrayFromFunction = ${arrayFromFunction[i]}")


    //Packages allows to split the code into namespaces , both functions ans class have their full name qualifier extendes
    //by the package namespace

    //importing wildcards package.* is useful when we have to import a lot of constants


    //String concatenation

    val string2 = "Sam"
    val concatenated = "Hello " + string2

    // string templates, they are lke an upgrade of string concatenation and they allow to use multiple variables in a single string literal
    //More complex templates can be used with brackets

    val template = "Hello $string2, the length of your name is ${string2.length}"

    //Ranges

    //ranges can be created with the .. operator, every compatible type can be implemented in ranges

    val aToZ = "a".."z"
    val aaToZz = "aa".."zz"
    val oneToHundred = 1..100

    //the in operator can be used to test if a value is effectively inside the range

    val theC = "c"
    val isAlsTrue = theC in aToZ
    val isFalseFalse = 1000 in oneToHundred

    //There are two additional functions to create ranges that extends numeric types and the functionalities provided by the .. operator
    //they are the downTo() and rangeTo() methods, defined as extension functions from Numeric Types

    val newRange = 100.downTo(0)
    val newNewRange = 0.rangeTo(560).step(2)

    println("$newNewRange")

    //Cannot use negative numbers to create a decreasing range, finally ranges can be reversed with reverse()

    val reverseRange = (0..100).step(2).reversed()
    var z = 10

    while( z > 5) {
        println("Z is between 5 and ten $z")
        z--
    }

    //For Loop
    //The for loop is used to iterate over any object that defines a function or extension function with name iterator

    val list = listOf(1,2,3,4)

    for(elem in list) {
        println(elem)
    }

    val set = setOf(1,2,3,4,5,6,7,8)
    for(elem in set) {
        println(elem)
    }


    for(i in newNewRange) {
        for(j in 1..10){
            println(i*j)
        }
    }

    //Any object can be used in a For-loop provided that it implements a function called iterator making this an extremely flexible construct
    // This function must return an istance of an object that implements those two functions

    //operator fun hasNext(): Boolean
    //operator fun next(): T (T is the generic item)

    //In the standard string class Kotlin provides an extension function iterator that adheres to the required contracts and so strings
    //can be used is a for loop to iterate over the individual characters

    //Array have the opportunity to address indices


    //EXCEPTIONS

    //all exceptions in Kotlin are unchecked:
    //Unchecked = Don't need to be added to method signature
    //Checked, need to be added to method signature, for example if NullPointerException was checked it would have been
    //implemented everywhere


    //Code that needs to be handled safely has to be put in the "try" block, zero or more "catch" block can be added to handle
    //different exceptions, "finally" is the code that is always executed

    //Example of basic handling of an exception PAGE 41

    fun readFile (path: Path) : Unit {
        val input = Files.newInputStream(path)
        try {
            var byte = input.read()
            while (byte != -1) {
                println(byte)
                byte = input.read()
            }
        }
        catch (e: IOException) {
            println("Error reading data from file, Error is ${e.message}")
        }
        finally {
            input.close()
        }
    }

    //Referential Equality and Structural Equality:
    //When working with OOP there are two concepts of equality, one is when two objects have the same reference in memory
    //and the other is when two objects have different reference in memory but the same value


    val a = "name"
    val b = "name"
    var bool = a === b //referential equality, if two objects point at the same istance in memory


    println("$bool")

    var bool2 = a == b //structural equality, if two objects have the same value

    println("$bool2")

    // THIS EXPRESSION, referring to the enclosing instance , for example an instance may want to invoke a method passing
    // itself as an argument

    class Person (name: String) {
        fun printMe() = println(this)
        private fun age(): Int = 21
    }

    //In kotlin terminology the reference referred by "this" keyword is called the RECEIVER , this is because it was the
    //instance that received the invocation of the function
    //In members of a class, "this" refers to the class instance. In extension functions, this refers to the instance that
    //the extension function was applied to


    // Scope
    // In nested scopes we may want to refer to outer instances, so we need to use "this" accordingly with labels

    class Building ( val address: String) {
        inner class Reception(telephone: String) {
            fun printTelephone() = println(this) //this print the telephone
            fun printAddres() = println(this@Building.address) //need to declare address a val
        }
    }

    // Visibility modifiers

    //Usually not all functions are designed to be part of a public API, some will be private, some other need to be
    //accessible to package level etc , the modifiers in kotlin are 4 public,internal,protected,private

    // DEFAULT = PUBLIC

    // Private
    // Inside a class, interface or object, a private function/property is accessible only by that specific class
    // Any top level function, interface or class defined private is accessible only that specific file

    //Protected
    //top level functions, interfaces or classes and objects cannot be declared as protected
    //Any function or property declared as protected inside a class or interface is accessible only by memebrs of that class
    //Or by subclasses of that class

    //Internal
    // Internal deals with the concept of module, a module is defined as a Gradle or Maven module, any code marked as


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //EXPRESSIONS
    //In control flow, an expression is a statement that evaluates to a value, so it can be passed, as a parameter , to a function etc
    //A statement on the other hand, has no returning value returned

    val startsWith = "hello".startsWith("h") //this expression evaluates to true

    val aa = 1 //is a simple statement, it assign the number to the variable (final) and it doesnt retun anything , or doesn't evaluate to anything by itself

    //In java, if...else try...catch are statements, they don't return values and it's usual to use another variable like
    fun isZero(int: Int) : Boolean {
        if(int == 0) return true
        else return false
    } //if, else as a statement in Java, in Kotlin however they can be evaluated as expressions , this means that they can be assigned to a value
    //returned from a function or passed s argument to another function

    //In Kotlin the if..else control blocks are expressions, this means the results can be direcly assigned to a value/variable, returned from a function and passsed as an
    //argument to another function


    val date = Date()
    val today = if(date.year == 2017) true else false

    fun isZeroNew (int: Int) : Boolean = if(int == 0) true else false

    fun isZeroNew2 (x: Int) : Boolean {
        return if(x==0) true else false
    }


    //the powerful use of expressions in kotlin

 /*   //Similar to try..catch

    val result = try {
        readfile()
        true
    } catch (e: IOException) {
        false
    } //the value of results can be true if readfil goes well or false if it raises an exception*/


    ////////////////////// Null Syntax

    //Variables that can be null has to be declared with ?

    var str: String? = null



    //Type casting, if an object is declared as A, but we want to test a more specific version B we can use is

    fun isString(obj: Any): Boolean {
        if (obj is String) return true
        else return false
    }


    //////////////////////////////////////////////////////// Smart Casts


    //In java if we want to refer to a more specific instance of an object we have to smart cast it PAGE 47, in KOtlin we just have to do

    fun printStringLength(any: Any) {
        if (any is String) println("${any.length}") //already casted, in JAva it would have been
    }

    //JAva version for cast
 /*   public void printStringLength(Object obj) {
        if (obj instanceof String) {
            String str = (String) obj //need to be recasted
                    System.out.print(str.length())
        }
    }*/


    //Explicit casting

    fun length(any: Any): Int {
        val str : String = any as String
        return str.length
    }

    //as? safe cast operator
    // the null variable cannot be cast to a type that is not declared nullable, so if any would have been null we would have got an error
    // we need to declare str as nullable

    val any = "/home/users"
    val string10 : String? = any as String
    val file : File? = any as? File // file will be null here


    //////////////////////////////////////////////////// WHEN

    //When is similar to java Switch

    fun whatNumber(x: Int) {
        when(x) {
            0 -> println("the number is zero")
            1 -> println("the number is one")
            else -> println ("the number is neither zero or one")
        }
    }

    fun isZeroOrOne(x: Int): Boolean {


        val isTrue = when(x) {
            0,1 -> true
            else -> false
        }
        return when(x) {
            0,1 -> true
            else -> false
        }


    }

    //////////////////Function Return

    fun addTwoNumbers(a: Int, b: Int): Int {
        return a+b
    }

    //By default return returns to the nearest function called

    fun largestNumber(a: Int, b: Int, c: Int): Int {
        fun largest(a: Int, b: Int) : Int{
            if(a>b) return a
            else return b

        }
        return largest(largest(a,b), largest(b,c)) //invoke largest on two functions that
    }

    fun printUntilStop(list: List<String>) {
        val list = list
        val list2 = listOf<String>("a", "b", "stop", "d", "stop", "e")
        list2.forEach stop@{     if(it=="stop") return@stop
                            else println(it)
        }

        ////Type hierarchy

        //In kotlin the uppermost type is called Any. This is analogous to Object, it defines toString, hashCode and equals methods
        //It also defines the extension methods apply, let and to
    }

}

