package com.maple.useraccount.dao;

import com.maple.useraccount.service.model.UserAccountEntity;
import com.maple.useraccount.service.model.UserImageEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserImageDao extends JpaRepository<UserImageEntity, String> {

  Optional<UserImageEntity> findByUserAccountId(String userAccountId);
}
