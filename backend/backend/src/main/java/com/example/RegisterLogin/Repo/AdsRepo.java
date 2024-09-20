package com.example.RegisterLogin.Repo;

import com.example.RegisterLogin.Entity.Ads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdsRepo extends JpaRepository<Ads, Integer> {
}
