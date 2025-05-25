package org.example

import java.util.UUID

data class Transaction(
    val transactionId: Int = UUID.randomUUID().hashCode(),
    val from: Int,
    val to: Int,
    val amount: Double,
    val date: Long
)
