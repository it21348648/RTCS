package com.example.hireme

class BuyerValidation {

    fun validateData(BuyerName:String, displayName:String, Email:String, Password:String):Boolean{

        if(BuyerName.isEmpty()){
            return false
        }
        if(displayName.isEmpty()){
            return false
        }
        if(Email.isEmpty()){
            return false
        }
        if(Password.isEmpty()){
            return false
        }

        return true
    }

}