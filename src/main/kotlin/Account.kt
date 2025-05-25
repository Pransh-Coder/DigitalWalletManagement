package org.example

data class Account(
    val accountNumber: Int = Utils.generateAccountNumber(),
    val user: User,
    val currentBalance: Double,
    val transactions: Set<Transaction> = emptySet<Transaction>()
)


