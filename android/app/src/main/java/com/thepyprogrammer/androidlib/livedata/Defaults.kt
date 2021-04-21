package com.thepyprogrammer.androidlib.livedata

import java.math.BigDecimal
import java.math.BigInteger


fun hasParameterlessPublicConstructor(clazz: Class<*>): Boolean {
    for (constructor in clazz.constructors) {
        // In Java 7-, use getParameterTypes and check the length of the array returned
        if (constructor.parameterCount === 0) {
            return true
        }
    }
    return false
}

@ExperimentalUnsignedTypes
fun getDefaultValue(clazz: Class<*>) = run {
    if(hasParameterlessPublicConstructor(clazz)) clazz.newInstance()
    else {

        when (clazz) {
            Boolean::class.java -> true


            Byte::class.java -> 1.toByte()
            Short::class.java -> 1.toShort()
            Int::class.java -> 1
            Long::class.java -> 1.toLong()


            Double::class.java -> 1.0
            Float::class.java -> 1.0f


            BigInteger::class.java -> 1.toBigInteger()
            BigDecimal::class.java -> 1.toBigDecimal()


            UByte::class.java -> 1.toUByte()
            UInt::class.java -> 1.toUInt()
            ULong::class.java -> 1.toULong()
            UShort::class.java -> 1.toUShort()


            String::class.java -> ""
            Character::class.java -> ' '


            List::class.java -> listOf<Any>()
            Array::class.java -> arrayOf<Any>()


            else -> null
        }
    }
}!!

