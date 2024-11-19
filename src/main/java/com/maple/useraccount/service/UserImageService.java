package com.maple.useraccount.service;

import com.maple.useraccount.service.model.UserImageEntity;

public interface UserImageService {

  UserImageEntity get(String userAccountId);

  UserImageEntity save(UserImageEntity entity);

  void delete(UserImageEntity entity);
}