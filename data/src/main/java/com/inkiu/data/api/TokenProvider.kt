package com.inkiu.data.api

interface TokenProvider {
    val token: String
    val tokenSecret: String
}