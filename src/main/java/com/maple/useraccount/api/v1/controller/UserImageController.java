package com.maple.useraccount.api.v1.controller;

import com.maple.useraccount.api.v1.response.builder.UserImageApiHandler;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController("UserImageControllerV1")
@AllArgsConstructor
@RequestMapping("/api/v1/user-accounts/{userAccountId}/images")
public class UserImageController {

  private final UserImageApiHandler apiHandler;

  @GetMapping("/full-size")
  public ResponseEntity<byte[]> getFullImage(@PathVariable final String userAccountId) {
    return apiHandler.handleGetFullSize(userAccountId);
  }

  @GetMapping("/thumbnail")
  public ResponseEntity<byte[]> getThumbnail(@PathVariable final String userAccountId) {
    return apiHandler.handleGetThumbnail(userAccountId);
  }

  @PostMapping()
  public void upload(
      @PathVariable final String userAccountId, @RequestParam("image") final MultipartFile image)
      throws IOException {
    apiHandler.handlePost(userAccountId, image);
  }

  @DeleteMapping()
  public void delete(@PathVariable final String userAccountId) {
    apiHandler.handleDelete(userAccountId);
  }
}
