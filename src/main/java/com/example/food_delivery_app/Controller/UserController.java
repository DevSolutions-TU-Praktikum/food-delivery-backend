package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Repository.UserRepository;
import com.example.food_delivery_app.Service.UserService;
import com.example.food_delivery_app.dto.LoginDto;
import com.example.food_delivery_app.dto.SignUpDto;
import com.example.food_delivery_app.Entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody SignUpDto dto) {
        UserEntity createdUserEntity = userService.createUser(dto);
        return ResponseEntity.ok(createdUserEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<UserEntity> user = userRepository.findByUsername(loginDto.getUsername());

        if (user.isPresent()) {
            UserEntity loggedInUser = user.get();
            Optional<UserEntity> userDetail = userService.getUserById(loggedInUser.getId());

            if (userDetail.isPresent()) {
                return ResponseEntity.ok(userDetail.get());
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("User found, but additional details not found");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not found");
        }
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PatchMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity userEntity) {
        try {
            int userId = userRepository.findByUsername(userEntity.getUsername())
                    .map(UserEntity::getId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found."));

            UserEntity updatedUser = userService.updateUser(userId, userEntity);
            return ResponseEntity.ok(updatedUser);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user.");
        }
    }
}