package com.AlaCartApp.controller;

import com.AlaCartApp.models.request.UserDto;
import com.AlaCartApp.service.abstraction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/try")
    public ResponseEntity<UserDto> createDTO(@RequestBody UserDto userDto){
        return new ResponseEntity(userDto, HttpStatus.OK);
    }
   /* @PostMapping
    public ResponseEntity<UserDto> create (@RequestBody UserDto userDto){
        if(userService.isUserEmailDuplicate(userDto.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
            return userService.create(userDto).map(t -> new ResponseEntity<>(t, HttpStatus.CREATED))
                    .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getUsers(){
        Optional<List<UserDto>> userDtoListOptional = userService.userList();

        if(userDtoListOptional.isPresent()){
            List<UserDto> userList = userDtoListOptional.get();
            return new ResponseEntity<>(userList, HttpStatus.OK);
        }
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
       return userService.userId(id).map(t -> new ResponseEntity<>(t, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping("/update")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto){
        return userService.update(userDto).map(t -> new ResponseEntity<>(t, HttpStatus.ACCEPTED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<UserDto> currentUserDto = userService.userId(id);

        if(currentUserDto.isPresent()){
            userService.delete(id);
            return new ResponseEntity<>("Se elimino el usuario correctamente", HttpStatus.OK);
        }
            return new ResponseEntity<>("El usuario ingresado no existe.", HttpStatus.NOT_FOUND);
    }*/

}
