package com.example.springboot_with_kotlin.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @NotNull(message = "이름이 누락 되었습니다.")
        @Size(min=2,max=5, message = "이름은 최소2글자에서최대 5글자입니다.")
        @RequestParam name : String,

        @NotNull(message = "age 값이 누락 되었습니다.")
        @Min(value = 20, message = "20보다 커야 합니다.")
        @RequestParam age : Int
    ) : String{
        return "$name-$age";
    }


    @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
    fun deleteMappingPath(
        @PathVariable(value="name") _name : String,
        @PathVariable(name="age") _age:Int
    ) :String {
        return "$_name-$_age";
    }
}