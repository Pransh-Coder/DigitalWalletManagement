package org.example

class WalletRepository  {

    //1. Create Wallet
    //2. Transfer Amount
    //3. Account Statement/Transaction History
    //4. Balance Wallet Overview

    //Int -- AccountNumber
    private val walletHashMap : HashMap<Int,Account> = HashMap()

    fun createWallet(name:String, amount:Double) {
        if (amount <= 0.0){
            throw NumberFormatException("The amount cannot be negative or zero")
        }

        val user = User(name = name)
        val account = Account(user = user, currentBalance = amount)
        println("Account created for $name with account number ${account.accountNumber}")
        walletHashMap.put(key = account.accountNumber, value = account)
    }

    fun transferAmount(fromAccNum: Int, toAccNum: Int,amount: Double) {
        if (validateInputs(fromAccNum,toAccNum,amount).not()){
            return
        }

        val transaction = Transaction(
            from = fromAccNum,
            to = toAccNum,
            amount = amount,
            date = System.currentTimeMillis()
        )

        val fromAccountObj = walletHashMap.get(fromAccNum)
        val toAccountObj = walletHashMap.get(toAccNum)

        println("Account objs before transfer fromAccountObj = $fromAccountObj || toAccountObj = $toAccountObj")

        //subtract money from FROM_ACCOUNT wallet account
        val fromLeftAccountBalance = fromAccountObj?.currentBalance?.minus(amount)

        //add the money to TO_ACCOUNT wallet account
        val toNewAccountBalance = toAccountObj?.currentBalance?.plus(amount)

        //updating the hashMap with updated balance
        fromAccountObj?.accountNumber?.let {
            walletHashMap.put(
                it,
                fromAccountObj.copy(currentBalance = fromLeftAccountBalance!!, transactions = fromAccountObj.transactions.plus(transaction))
            )
        }

        toAccountObj?.accountNumber?.let {
            walletHashMap.put(
                it,
                toAccountObj.copy(currentBalance = toNewAccountBalance!!, transactions = toAccountObj.transactions.plus(transaction))
            )
        }

        //You need to retrieve the updated versions of the accounts from the walletHashMap after the transfer
        println("Account objs AFTER** transfer fromAccountObj = ${walletHashMap.get(fromAccNum)} || toAccountObj = ${walletHashMap.get(toAccNum)}")
    }

    private fun validateInputs(fromAccNum: Int, toAccNum: Int, enteredAmount: Double): Boolean{
        if (fromAccNum == toAccNum){
            println("Cannot transfer money to same accounts!")
            return false
        }
        if (enteredAmount < 1){
            println("Entered amount must be greater than 0!")
            return false
        }
        if (fromAccNum == 0 || toAccNum == 0){
            println("Enter correct account number!")
            return false
        }
        val fromAccObj = walletHashMap.get(fromAccNum)
        if (fromAccObj?.currentBalance!! < enteredAmount){
            println("Current wallet balance is lower than $enteredAmount!")
            return false
        }
        //--------- I had forgotten these---------------
        if (walletHashMap.containsKey(fromAccNum).not()){
            println("Incorrect From Account Number")
            return false
        }
        if (walletHashMap.containsKey(toAccNum).not()){
            println("Incorrect To Account Number")
            return false
        }

        return true
    }

    fun getAccountStatement(accountNumber: Int){

        if (accountNumber == 0){
            println("Invalid account number!")
            return
        }

        val accDetails = walletHashMap.get(accountNumber)

        val currentBalance = accDetails?.currentBalance?:0.0
        val transactionSet = accDetails?.transactions.orEmpty()

        println("Summary for account number: $accountNumber")
        println("Current Balance: $currentBalance")
        println("Your Transaction History----------")
        println(transactionSet)
    }

    fun overviewWalletBalances(){
        walletHashMap.forEach {
            println("Current balance for accNum ${it.key} - Rs ${it.value.currentBalance}" )
        }
    }
}