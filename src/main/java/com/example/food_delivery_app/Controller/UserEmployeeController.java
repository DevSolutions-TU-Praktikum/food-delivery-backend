package com.example.food_delivery_app.Controller;

import com.example.food_delivery_app.Entity.UserEmployee;
import com.example.food_delivery_app.Service.UserEmployeeService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins ="localhost:5500")
@RequestMapping("/userEmployee")
public class UserEmployeeController {

    private final UserEmployeeService userEmployeeService;

    @Autowired
    public UserEmployeeController(UserEmployeeService userEmployeeService) {
        this.userEmployeeService = userEmployeeService;
    }

    @GetMapping
    public ResponseEntity getAllUserEmployees() {
        return ResponseEntity.ok(getAllUserEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserEmployeeById(@PathVariable int id) {
        Optional<UserEmployee> found = userEmployeeService.getUserEmployeeById(id);
        return found.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserEmployee> createMenuItem(@RequestBody UserEmployee userEmployee) {
        UserEmployee created = userEmployeeService.createUserEmployee(userEmployee);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEmployee> updateEmployee(@PathVariable int id, @RequestBody UserEmployee updatedEmployee) {
        UserEmployee updated = userEmployeeService.updateUserEmployee(id, updatedEmployee);
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
