package com.example.agentservice.repository;


import com.example.agentservice.model.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TerminalRepository extends JpaRepository<Terminal,Long> {
    List<Terminal> findByMerchantsIsNullAndUserId(String userID);
    List<Terminal> findAllByMerchantsIsNullAndMerchants_UserId(String userID);
    Terminal findByIdAndMerchantsNotNullAndUserId(Long id,String userId);
    List<Terminal>findByMerchants_IdAndUserId(Long id, String userId);
    List<Terminal>findByMerchants_IdAndMerchantsUserId(Long id, String userId);
}
