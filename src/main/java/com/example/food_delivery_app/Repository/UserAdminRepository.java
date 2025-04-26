package com.example.food_delivery_app.Repository;

import com.example.food_delivery_app.Entity.UserAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAdminRepository extends JpaRepository<UserAdmin, Integer> {
}
