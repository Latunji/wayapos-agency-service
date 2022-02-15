package org.wayapos.agencyService.digitalIdentityService.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wayapos.agencyService.digitalIdentityService.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>{

}
