package com.example.springboot_with_kotlin.controller.post

import com.example.springboot_with_kotlin.model.UserRequest
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("post-mapping")
    fun postMapping(): String {
        return "request-mapping";
    }

    @RequestMapping(method = [RequestMethod.POST], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    //object mapper
    // json -> object , object -> json
    @PostMapping("/post-mapping/object")
    fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
        return userRequest;
    }

}