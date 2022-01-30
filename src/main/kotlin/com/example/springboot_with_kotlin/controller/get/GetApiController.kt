package com.example.springboot_with_kotlin.controller.get

import com.example.springboot_with_kotlin.model.UserRequest
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping(path=["/hello","/abc"]) // 이렇게 2개 이상의 uri도 등록 가능!
    fun hello(): String{
       return "hello kotlin";
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping";
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name :String, @PathVariable age : Int): String{
        print(name)
        return "$name $age";
    }

    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value ="name") _name :String, @PathVariable(name="age") age : Int): String{ // 변수 이름이 중복 되는 경우에
        var name ="kotlin"
        return "$_name $age";
    }

    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam(value="name") name : String,
        @RequestParam(name="age") age:Int
    ): String{
        return "$name $age";
    }

    // phone-number 하이픈이 들어가있는 변수명은 코틀린에서 지원하지 않기 때문에 쿼리 스트링으로 받아야함 !
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest) : UserRequest{
        return userRequest;
    }

    // 여기에서는 하이픈 변수명이 사용 가능
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map : Map<String,Any>): Map<String, Any> {
        print(map);
        var phoneNumber = map["phone-number"];
        print(phoneNumber);
        return map;
    }

}