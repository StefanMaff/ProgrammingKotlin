package main.kotlin.KotlinOOP

/**
 * Created by stese on 26/07/2017.
 */

fun firstCharacterOfString(str : String) : Char {

    if (str.isEmpty()) { throw IllegalArgumentException()}
    return str.first()
}
