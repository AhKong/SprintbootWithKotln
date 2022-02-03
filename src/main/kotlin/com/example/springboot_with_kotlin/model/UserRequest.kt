package com.example.springboot_with_kotlin.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest (
        var name:String?=null,
        var age:Int?=null,
        var email:String?=null,
        var address:String?=null,
        var phoneNumber:String?=null,  //json에서는 스네이크 형식을 주로 사용한다고함.
)