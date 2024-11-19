package com.maple.useraccount.api.v1.controller;

import com.alviss.commons.api.model.Response;
import com.alviss.commons.security.SecurityService;
import com.maple.useraccount.api.v1.request.UserAccountRequest;
import com.maple.useraccount.api.v1.response.UserAccountResponse;
import com.maple.useraccount.api.v1.response.builder.UserAccountApiHandler;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("UserAccountControllerV1")
@AllArgsConstructor
@RequestMapping("/api/v1/organizations/{organizationId}/user-accounts")
public class UserAccountController {

  private final UserAccountApiHandler apiHandler;
  private final SecurityService securityService;

  @GetMapping("/{id}")
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_GET')")
  public Response<UserAccountResponse> get(
      @PathVariable final String id, @PathVariable final String organizationId) {
    return apiHandler.handleGet(id, organizationId);
  }

  @GetMapping
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_GET')")
  public Response<List<UserAccountResponse>> getAll(
      @PathVariable final String organizationId, final Pageable pageable) {
    return apiHandler.handleGetAll(organizationId, pageable);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_CREATE')")
  public Response<UserAccountResponse> post(
      @PathVariable final String organizationId, @RequestBody final UserAccountRequest request) {
    return apiHandler.handlePost(organizationId, request);
  }

  @PatchMapping("/{id}")
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_UPDATE')")
  public Response<UserAccountResponse> patch(
      @PathVariable final String organizationId,
      @PathVariable final String id,
      @RequestBody final UserAccountRequest request) {
    return apiHandler.handlePatch(id, organizationId, request);
  }

  @PutMapping("/{id}")
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_UPDATE')")
  public Response<UserAccountResponse> put(
      @PathVariable final String organizationId,
      @PathVariable final String id,
      @RequestBody final UserAccountRequest request) {
    return apiHandler.handlePut(id, organizationId, request);
  }

  @DeleteMapping("/{id}")
  //    @PreAuthorize("@securityService.hasPermission('ROLE_USER_DELETE')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable final String organizationId, @PathVariable final String id) {
    apiHandler.handleDelete(id, organizationId);
  }
}
