package com.maple.useraccount.dao;

import com.maple.useraccount.service.model.UserAccountEntity;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountDao extends JpaRepository<UserAccountEntity, String> {

  Page<UserAccountEntity> findAllByOrganizationId(String organizationId, Pageable pageable);

  Optional<UserAccountEntity> findByIdAndOrganizationId(String id, String organizationId);
}
