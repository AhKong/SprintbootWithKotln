package com.example.springboot_with_kotlin.controller.response

import com.example.springboot_with_kotlin.model.UserRequest
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get 4xx
    @GetMapping("")
    fun getMapping(@RequestParam age : Int): ResponseEntity<String> {

      return age?.let {
          if(it <20){
              return ResponseEntity.status(400).body("age 값은 20 보다 커야 합니다.")
          }
          ResponseEntity.ok("ok");
      }?: kotlin.run {
          return ResponseEntity.status(400).body("age 값이 누락 되었습니다.")
      }
    }

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서새로 생성했다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id : Int): ResponseEntity<Nothing> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)

    }
}