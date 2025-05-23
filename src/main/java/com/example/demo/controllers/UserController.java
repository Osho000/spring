package com.example.demo.controllers;

import com.example.demo.entity.Payloads.UserPayload;
import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {
    private final UserService userService;

//    @GetMapping()
//    List<User> getUsers() {
//        return userService.findUsers();
//    }

//    @GetMapping("/{userid}")
//    User getUser(@PathVariable("userid") int id) {
//        return userService.findUser(id).orElseThrow(() -> new NoSuchElementException("User not found"));
//    }
//
//
//
//
//
//
//    @PostMapping()
//    void createUser(@RequestBody User user) {
//        userService.saveUser(user);
//    }
//
//    @PutMapping("/{id}")
//    void updateUser(@PathVariable int id, @RequestBody @Valid UserPayload user) {
//        userService.updateUser(id, user);
//    }
//
//    @DeleteMapping("/{id}")
//    void deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//    }
////    @DeleteMapping()
////    void deleteUser(@RequestParam(required = false) int id) {
////        userService.deleteUser(id);
////    }
//








    @Operation(summary = "получить всех пользователей", tags = {"Users"}, description = "fish text",responses = {
            @ApiResponse(responseCode = "200",description = "Юзеры найден")
    },parameters =
    @Parameter(name = "id", description = "Id user",required = true))
    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }


    @Operation(summary = "получить пользователя", tags = {"Users"}, description = "fish text",responses = {
            @ApiResponse(responseCode = "302",description = "Юзер найден"),
            @ApiResponse(responseCode = "404",description = "Юзер не найден")
    },parameters =
    @Parameter(name = "id", description = "Id user",required = true))
    @GetMapping("/{userid}")
    public ResponseEntity<User> getUser(@PathVariable("userid")int id){
        return userService.findUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Создать пользователя",operationId = "createuser", tags = {"Users"}, description = "fish text",responses = {
            @ApiResponse(responseCode = "201",description = "Cоздать юзера")
    },parameters =
    @Parameter(name = "id", description = "Id user",required = true))
    @PostMapping

    public ResponseEntity<User> createUser(@RequestBody UserPayload user){
        User saved = userService.saveUser(user);
        return ResponseEntity.status(201).body(saved);
    }

    @Operation(summary = "Обновление пользователя", tags = {"Users"}, description = "fish text",responses = {
            @ApiResponse(responseCode = "204",description = "Нет содержимого"),
            @ApiResponse(responseCode = "404",description = "Юзер не найден")
    },parameters =
    @Parameter(name = "id", description = "Id user",required = true))
    @PostMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody UserPayload user){
        Optional<User> existingUser = userService.findUser(id);
        if(existingUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }







        userService.updateUser(id,user);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Удалить пользователя", tags = {"Users"}, description = "fish text",responses = {
            @ApiResponse(responseCode = "200",description = "Юзер удален"),
            @ApiResponse(responseCode = "404",description = "Юзер не найден и не удален")
    },parameters =
    @Parameter(name = "id", description = "Id user",required = true))
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam(required = false)Integer id){
        if(id == null || userService.findUser(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

















}
