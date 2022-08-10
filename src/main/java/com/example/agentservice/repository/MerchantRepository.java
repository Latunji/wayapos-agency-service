package com.example.agentservice.repository;

import com.example.agentservice.model.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MerchantRepository extends JpaRepository<Merchants,Long> {
    Optional<Merchants> findByMerchantIdAndUserId(String merchantId,String userId);
    Optional<Merchants> findByEmail(String email);

    Optional<Merchants> findByUserId(String userId);
    List<Merchants> findByAdmin(boolean email);
}
