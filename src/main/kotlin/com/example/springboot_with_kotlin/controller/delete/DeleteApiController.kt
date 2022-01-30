package com.example.springboot_with_kotlin.controller.delete

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
        @RequestParam name : String,
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