package com.example.hireme

import org.junit.Assert.*
import org.junit.Test

class BuyerValidationTest{

    @Test
    fun `empty Name return False`(){
        val validateName = BuyerValidation()

        val result = validateName.validateData(
            "",
            "ravindu888",
            "ravindu.chamara2000@gmail.com",
            "123456"
        )
        assertEquals(false, result)
    }


    @Test
    fun `empty userName return False`(){
        val validateuserName = BuyerValidation()

        val userNameResult = validateuserName.validateData(
            "Ravindu Chamara",
            "",
            "ravindu.chamara2000@gmail.com",
            "123456"
        )
        assertEquals(false, userNameResult)
    }


    @Test
    fun `empty Email return False`(){
        val validateEmail = BuyerValidation()

        val EmailResult = validateEmail.validateData(
            "Ravindu Chamara",
            "ravindu888",
            "",
            "123456"
        )
        assertEquals(false, EmailResult)
    }

    @Test
    fun `empty Password return False`(){
        val validatePassword = BuyerValidation()

        val PasswordResult = validatePassword.validateData(
            "Ravindu Chamara",
            "ravindu888",
            "ravindu.chamara2000@gmail.com",
            ""
        )
        assertEquals(false, PasswordResult)
    }

}