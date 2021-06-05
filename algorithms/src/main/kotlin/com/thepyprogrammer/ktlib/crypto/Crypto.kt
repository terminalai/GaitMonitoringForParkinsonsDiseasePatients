package com.thepyprogrammer.ktlib.crypto

object Crypto {
    private val aes = AES()

    fun encrypt(value: String, key: String) = aes.encrypt(value, key)

    fun decrypt(value: String, key: String) = aes.decrypt(value, key)
}