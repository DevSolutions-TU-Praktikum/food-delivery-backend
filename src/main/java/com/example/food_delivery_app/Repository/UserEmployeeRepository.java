package com.example.food_delivery_app.Repository;

import com.example.food_delivery_app.Entity.UserEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmployeeRepository extends JpaRepository<UserEmployee, Integer> {
}
