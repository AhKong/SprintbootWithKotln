package com.example.springboot_with_kotlin.model



data class UserRequest (
        var name:String?=null,
        var age:Int?=null,
        var email:String?=null,
        var address:String?=null
)