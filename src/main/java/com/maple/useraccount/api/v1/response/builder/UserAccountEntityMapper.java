package com.maple.useraccount.api.v1.response.builder;

import com.maple.useraccount.api.v1.request.UserAccountRequest;
import com.maple.useraccount.api.v1.response.UserAccountResponse;
import com.maple.useraccount.api.v1.response.UserAccountResponse.UserAccountData;
import com.maple.useraccount.service.model.UserAccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAccountEntityMapper {

  public UserAccountEntity mapForPost(
      final String organizationId, final UserAccountRequest request) {
    return UserAccountEntity.builder()
        .organizationId(organizationId)
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .dob(request.getDob())
        .build();
  }

  public UserAccountEntity mapForPut(
      final UserAccountEntity entity, final UserAccountRequest request) {
    entity.setFirstName(request.getFirstName());
    entity.setLastName(request.getLastName());
    entity.setDob(request.getDob());
    return entity;
  }

  public UserAccountEntity mapForPatch(
      final UserAccountEntity entity, final UserAccountRequest request) {
    if (request.getFirstName() != null) {
      entity.setFirstName(request.getFirstName());
    }
    if (request.getLastName() != null) {
      entity.setLastName(request.getLastName());
    }
    if (request.getDob() != null) {
      entity.setDob(request.getDob());
    }
    return entity;
  }

  public UserAccountResponse entityToResponse(final UserAccountEntity entity) {
    return UserAccountResponse.builder()
        .id(entity.getId())
        .data(
            UserAccountData.builder()
                .organizationId(entity.getOrganizationId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .dateOfBirth(entity.getDob())
                .build())
        .build();
  }
}
