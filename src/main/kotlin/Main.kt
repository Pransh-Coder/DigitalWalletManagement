package org.example

fun main() {
    //println("Hello World!")

    val walletRepository = WalletRepository()

    walletRepository.createWallet(name = "Pransh Rastogi",50.0)

    walletRepository.createWallet(name = "Kanishk Kumar",100.0)

    walletRepository.transferAmount(fromAccNum = 1, toAccNum = 2, amount = 50.0)
    println()

    walletRepository.getAccountStatement(accountNumber = 1)
    println()
    walletRepository.getAccountStatement(accountNumber = 2)

    println()
    walletRepository.overviewWalletBalances()
}