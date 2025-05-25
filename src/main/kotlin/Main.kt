package org.example

/* POJOs / DATA class

User
--userId
--name

Account
--accountNum
--user: User
--currentBalance
--Set<Transactions>

Transaction
--transactionId
--fromName
--toName
--amount (sent/received)
--timeStampWithDate
----------------------------------
Create Wallet —  This option should create a wallet for the user.
walletId
name
initialAmount
qrCodeData
timeStamp

Transfer Amount
transactionId
name (sender/receiver info)
accountNum
amount (sent/received)
timeStamp
*/

fun main() {

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