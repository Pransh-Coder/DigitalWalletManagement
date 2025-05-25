package org.example

import java.util.UUID

data class User(
    val userId: String = UUID.randomUUID().toString(),
    val name: String
)
