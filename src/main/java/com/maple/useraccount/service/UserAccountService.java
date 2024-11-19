package com.maple.useraccount.service;

import com.maple.useraccount.service.model.UserAccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserAccountService {

  UserAccountEntity get(String id, String organizationId);

  Page<UserAccountEntity> getAll(String organizationId, Pageable pageable);

  UserAccountEntity save(UserAccountEntity entity);

  void delete(UserAccountEntity entity);
}