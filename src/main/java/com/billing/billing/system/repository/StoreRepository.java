package com.billing.billing.system.repository;

import com.billing.billing.system.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findByStoreAdminId(Long adminId);
}
