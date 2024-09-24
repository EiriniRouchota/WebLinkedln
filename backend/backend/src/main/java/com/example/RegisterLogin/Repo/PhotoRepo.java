package com.example.RegisterLogin.Repo;


import com.example.RegisterLogin.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepo extends JpaRepository<Photo, Integer> {
}
