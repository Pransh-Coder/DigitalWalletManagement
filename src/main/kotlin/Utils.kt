package org.example

object Utils{
    private var accountNumber = 1

    fun generateAccountNumber(): Int {
        return accountNumber++
    }
}
