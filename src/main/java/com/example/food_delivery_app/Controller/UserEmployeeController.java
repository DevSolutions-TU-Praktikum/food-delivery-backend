package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.UserEmployeeEntity;
import com.example.food_delivery_app.Repository.UserEmployeeRepository;
import com.example.food_delivery_app.Service.UserEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userEmployee")
public class UserEmployeeController {

    private final UserEmployeeService userEmployeeService;
    private final UserEmployeeRepository userEmployeeRepository;

    @Autowired
    public UserEmployeeController(UserEmployeeService userEmployeeService, UserEmployeeRepository userEmployeeRepository) {
        this.userEmployeeService = userEmployeeService;
        this.userEmployeeRepository = userEmployeeRepository;
    }

    @GetMapping
    public String getAllUserEmployees() {
        return userEmployeeRepository.findAll().toString();
    }

    @GetMapping("/{id}")
    public String getUserEmployeeById(@PathVariable int id) {
        return userEmployeeRepository.findById(id).toString();
    }

    @PostMapping
    public ResponseEntity<UserEmployeeEntity> createMenuItem(@RequestBody UserEmployeeEntity userEmployee) {
        UserEmployeeEntity created = userEmployeeService.createUserEmployee(userEmployee);
        return ResponseEntity.ok(created);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserEmployeeEntity> updateEmployee(@PathVariable int id, @RequestBody UserEmployeeEntity updatedEmployee) {
        UserEmployeeEntity updated = userEmployeeService.updateUserEmployee(id, updatedEmployee);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable int id) {
        boolean deleted = userEmployeeService.deleteUserEmployee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
