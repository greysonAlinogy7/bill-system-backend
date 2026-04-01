package com.billing.billing.system.repository;

import com.billing.billing.system.model.Store;
import com.billing.billing.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
