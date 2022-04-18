package com.example.springboot_with_kotlin.controller.exception

import com.example.springboot_with_kotlin.controller.ErrorResponseModel.Error
import com.example.springboot_with_kotlin.controller.ErrorResponseModel.ErrorResponse
import com.example.springboot_with_kotlin.model.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolationException
import javax.validation.Valid
import javax.validation.constraints.*


@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(){
      val list = mutableListOf<String>()
      val temp = list[0]
    }

    @GetMapping("")
    fun get(
        @NotBlank
        @Size(min=2, max = 6)
        @RequestParam name:String,

        @Max(10)
        @RequestParam age : Int) : String{

        print(name);
        print(age);

        return "$name-$age";
    }

    @PostMapping("")
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest;
    }



    // 클래스 내부에도 작성 가능 작성한 컨트롤러에서만 동작 가능함.
    @ExceptionHandler(value =[IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException (e : IndexOutOfBoundsException): ResponseEntity<String> {
        println("controller Exception Handler :-)~~~")
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error");
    }

    @ExceptionHandler(value =[ConstraintViolationException::class])
    fun constraintViolationException(e : ConstraintViolationException,request : HttpServletRequest) : ResponseEntity<ErrorResponse>{
        // 1. 에러 분석
        val errors = mutableListOf<Error>()
        e.constraintViolations.forEach {
            val error = Error().apply {
                this.field = it.propertyPath.last().name
                this.message = it.message
                this.value = it.invalidValue
            }
        }
        // 2. Error response

        val errorResponse = ErrorResponse().apply {
            this.resultCode ="FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.toString()
            this.message ="요청에 에러가 발생하였습니다"
            this.path = request.requestURI.toString()
            this.httpMethod = request.method.toString()
            this.timeStamp = LocalDateTime.now()
            this.errors = errors
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(value =[MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException (e : MethodArgumentNotValidException,request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        val errors = mutableListOf<Error>()

        e.bindingResult.allErrors.forEach { errorObject ->
            val error = Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.value = errorObject.rejectedValue
            }
        }

        val errorResponse = ErrorResponse().apply {
            this.resultCode ="FAIL"
            this.httpStatus = HttpStatus.BAD_REQUEST.toString()
            this.message ="요청에 에러가 발생하였습니다"
            this.path = request.requestURI.toString()
            this.httpMethod = request.method.toString()
            this.timeStamp = LocalDateTime.now()
            this.errors = errors
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

}