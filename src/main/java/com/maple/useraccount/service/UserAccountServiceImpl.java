package com.maple.useraccount.service;

import com.alviss.commons.exception.NotFoundException;
import com.maple.useraccount.dao.UserAccountDao;
import com.maple.useraccount.service.model.UserAccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountDao dao;

  @Override
  public UserAccountEntity get(final String id, final String organizationId) {
    return dao.findByIdAndOrganizationId(id, organizationId)
        .orElseThrow(() -> new NotFoundException("Requested user account is not found"));
  }

  @Override
  public Page<UserAccountEntity> getAll(final String organizationId, final Pageable pageable) {
    return dao.findAllByOrganizationId(organizationId, pageable);
  }

  @Override
  public UserAccountEntity save(final UserAccountEntity entity) {
    return dao.save(entity);
  }

  @Override
  public void delete(final UserAccountEntity entity) {
    dao.delete(entity);
  }
}
