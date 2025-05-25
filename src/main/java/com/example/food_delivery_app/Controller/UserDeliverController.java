package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.UserDeliverer;
import com.example.food_delivery_app.Repository.UserDelivererRepository;
import com.example.food_delivery_app.Service.UserDelivererService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5500")
@RequestMapping("/userDeliverer")

public class UserDeliverController {
    private final UserDelivererService userDelivererService;
    private final UserDelivererRepository userDelivererRepository;

    @Autowired
    public UserDeliverController(UserDelivererService userDelivererService, UserDelivererRepository userDelivererRepository) {
        this.userDelivererService = userDelivererService;
        this.userDelivererRepository = userDelivererRepository;
    }

    @GetMapping
    public String getAllUserDeliverers() {
        return userDelivererRepository.findAll().toString();
    }

    @GetMapping("/{id}")
    public String getUserDelivererById(@PathVariable int id) {
        return userDelivererRepository.findById(id).toString();
    }

    @PostMapping

    public ResponseEntity<UserDeliverer> createMenuItem(@RequestBody @Valid UserDeliverer userDeliverer) {
        UserDeliverer created = userDelivererService.createUserDeliverer(userDeliverer);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDeliverer> updateDeliverer(@PathVariable int id, @RequestBody @Valid UserDeliverer updatedDeliverer) {
        UserDeliverer updated = userDelivererService.updateUserDeliverer(id, updatedDeliverer);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeliverer(@PathVariable int id) {
        boolean deleted = userDelivererService.deleteUserDeliverer(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
