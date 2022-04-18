package com.example.springboot_with_kotlin.controller.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException


@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(){
      val list = mutableListOf<String>()
      val temp = list[0]
    }

    // 클래스 내부에도 작성 가능 작성한 컨트롤러에서만 동작 가능함.
    @ExceptionHandler(value =[IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException (e : IndexOutOfBoundsException): ResponseEntity<String> {
        println("controller Exception Handler :-)~~~")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error");
    }
}