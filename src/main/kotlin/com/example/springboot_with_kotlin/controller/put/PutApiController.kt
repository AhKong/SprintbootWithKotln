package com.example.springboot_with_kotlin.controller.put

import com.example.springboot_with_kotlin.model.Result
import com.example.springboot_with_kotlin.model.UserRequest
import com.example.springboot_with_kotlin.model.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

    @PutMapping
    fun putMapping(): String {
        return "put-mapping";
    }

    @RequestMapping(method = [RequestMethod.PUT],path=["reuqest-mapping"])
    fun requestMapping(): String {
        return "request-mapping - put method"
    }

    @PutMapping("/put-mapping/object")
    fun putMappingObject(@Valid @RequestBody userRequest: UserRequest,bindingResult:BindingResult): ResponseEntity<String> {
        if(bindingResult.hasErrors()){
            //500 error
            var msg = StringBuilder()
            bindingResult.allErrors.forEach{
                var field = it as FieldError
                var message = it.defaultMessage
                msg.append("${field.field} : $message\n")

            }
            return ResponseEntity.badRequest().body(msg.toString())
        } else {
            return ResponseEntity.ok("success")
        }

       // 1. result
/*       return UserResponse().apply {
            this.result =  Result().apply {
                this.resultCode = "OK"
                this.resultMessage="성공"
            }
        // 2. description
        }.apply {
            this.description ="appliy 가 뭐냐??"
        // 3. user mutable list
        }.apply {
            var userList = mutableListOf<UserRequest>()
            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name = "0"
                this.age = 10
                this.email= "ssf.2com"
                this.address= "address"
                this.phoneNumber ="0111111"
            })
            userList.add(UserRequest().apply{
                this.name = "0bb"
                this.age = 10
                this.email= "ssbbf.2com"
                this.address= "adbbdress"
                this.phoneNumber ="0111bb111"
            })
           this.user =userList;
        }*/
    }
}