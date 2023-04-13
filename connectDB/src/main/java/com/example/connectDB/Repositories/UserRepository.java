package com.example.connectDB.Repositories;

import com.example.connectDB.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
