package com.maple.useraccount.service;

import com.alviss.commons.exception.NotFoundException;
import com.maple.useraccount.dao.UserImageDao;
import com.maple.useraccount.service.model.UserImageEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserImageServiceImpl implements UserImageService {

  private final UserImageDao dao;

  @Override
  public UserImageEntity get(final String userAccountId) {
    return dao.findByUserAccountId(userAccountId)
        .orElseThrow(() -> new NotFoundException("Requested user account image is not found"));
  }

  @Override
  public UserImageEntity save(final UserImageEntity entity) {
    return dao.save(entity);
  }

  @Override
  public void delete(final UserImageEntity entity) {
    dao.delete(entity);
  }
}
