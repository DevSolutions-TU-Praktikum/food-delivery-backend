package com.example.food_delivery_app.Controller;
import com.example.food_delivery_app.Service.AuthService;
import com.example.food_delivery_app.Service.UserService;
import com.example.food_delivery_app.Entity.User;
import com.example.food_delivery_app.dto.AuthResponseDto;
import com.example.food_delivery_app.dto.LoginDto;
import com.example.food_delivery_app.dto.SignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, AuthService authService, ModelMapper modelMapper1) {
        this.userService = userService;
        this.authService = authService;
        this.modelMapper = modelMapper1;
    }
    @GetMapping
    public ResponseEntity<Iterable<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> found = userService.getUserById(id);
        return found.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/signup")

    public ResponseEntity<AuthResponseDto> signup(@RequestBody SignUpDto signupDto) {
        if( userService.getUserByUsername( signupDto.getUsername() ).isPresent() && userService.getUserByUsername(signupDto.getUsername()).get().getPassword().equals( signupDto.getPassword()) ) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        User createdUser = userService.createUser(signupDto);
        int role = authService.getUserRole(createdUser.getId());

        AuthResponseDto response = new AuthResponseDto(
                createdUser.getId(),
                createdUser.getUsername(),
                role
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
//        Optional<User> user = userService.getUserByUsername(loginDto.getUsername());
//
//        if (user.isPresent()) {
//            User loggedInUser = user.get();
//            Optional<User> userDetail = userService.getUserById(loggedInUser.getId());
//
//            if (userDetail.isPresent()) {
//                return ResponseEntity.ok(userDetail.get()); // Returning User entity directly
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                        .body("User found, but additional details not found");
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("User not found");
//        }
//    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        Optional<User> userOpt = userService.getUserByUsername(loginDto.getUsername());

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not found");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(loginDto.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password");
        }

        // Optional: determine role
        int role = authService.getUserRole(user.getId()); // 0 = client, 1 = deliverer, 2 = employee

        // Return safe data
        return ResponseEntity.ok(new AuthResponseDto(user.getId(), user.getUsername(), role));
    }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody @Valid User user) {
        User updated = userService.updateUser(id, user);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable int Id) {
        boolean deleted = userService.deleteUser(Id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Item deleted successfully").toString());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Item not found").toString());
        }
    }
}