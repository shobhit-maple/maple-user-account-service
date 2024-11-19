package com.maple.useraccount.api.v1.response.builder;

import com.alviss.commons.api.model.Response;
import com.alviss.commons.exception.BadRequestException;
import com.alviss.commons.validator.CustomValidator;
import com.maple.useraccount.api.v1.request.UserAccountRequest;
import com.maple.useraccount.api.v1.response.UserAccountResponse;
import com.maple.useraccount.service.UserAccountService;
import com.maple.useraccount.service.model.UserAccountEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserAccountApiHandler {

  private final UserAccountService userAccountService;
  private final UserAccountEntityMapper mapper;
  private final CustomValidator validator;

  public Response<UserAccountResponse> handleGet(final String id, final String organizationId) {
    val entity = userAccountService.get(id, organizationId);
    return new Response<>(mapper.entityToResponse(entity));
  }

  public Response<List<UserAccountResponse>> handleGetAll(
      final String organizationId, final Pageable pageable) {
    val page = userAccountService.getAll(organizationId, pageable);
    return new Response<>(
        page.get().map(mapper::entityToResponse).collect(Collectors.toList()), page);
  }

  public Response<UserAccountResponse> handlePost(final String organizationId, final UserAccountRequest request) {
    val entity = mapper.mapForPost(organizationId, request);
    validate(entity);
    return new Response<>(mapper.entityToResponse(userAccountService.save(entity)));
  }

  public Response<UserAccountResponse> handlePatch(
      final String id, final String organizationId, final UserAccountRequest request) {
    val existingEntity = userAccountService.get(id, organizationId);
    val updatedEntity = mapper.mapForPatch(existingEntity, request);
    validate(updatedEntity);
    return new Response<>(mapper.entityToResponse(userAccountService.save(updatedEntity)));
  }

  public Response<UserAccountResponse> handlePut(
      final String id, final String organizationId, final UserAccountRequest request) {
    val existingEntity = userAccountService.get(id, organizationId);
    val updatedEntity = mapper.mapForPut(existingEntity, request);
    validate(updatedEntity);
    return new Response<>(mapper.entityToResponse(userAccountService.save(updatedEntity)));
  }

  public void handleDelete(final String id, final String organizationId) {
    val entity = userAccountService.get(id, organizationId);
    userAccountService.delete(entity);
  }

  private void validate(final UserAccountEntity entity) {
    val errors = validator.validate(entity);
    if (!errors.isEmpty()) {
      throw new BadRequestException("There are some validation errors", errors);
    }
  }
}
