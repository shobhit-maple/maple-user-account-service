package com.maple.useraccount.api.v1.response.builder;

import com.alviss.commons.exception.BadRequestException;
import com.alviss.commons.exception.NotFoundException;
import com.alviss.commons.validator.CustomValidator;
import com.maple.useraccount.service.UserImageService;
import com.maple.useraccount.service.model.UserImageEntity;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class UserImageApiHandler {

  private final UserImageService userImageService;
  private final CustomValidator validator;

  private static final int THUMBNAIL_SIZE = 128;
  private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpeg";
  private static final String IMAGE_FORMAT = "jpeg";

  public ResponseEntity<byte[]> handleGetFullSize(final String userAccountId) {
    val entity = userImageService.get(userAccountId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, DEFAULT_IMAGE_CONTENT_TYPE)
        .body(entity.getFullSize());
  }

  public ResponseEntity<byte[]> handleGetThumbnail(final String userAccountId) {
    val entity = userImageService.get(userAccountId);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_TYPE, DEFAULT_IMAGE_CONTENT_TYPE)
        .body(entity.getThumbnail());
  }

  public void handlePost(final String userAccountId, final MultipartFile image) throws IOException {
    val originalImage = ImageIO.read(image.getInputStream());
    val thumbnail = toThumbnail(originalImage);

    val fullSizeImage = toByteArray(originalImage);
    val thumbnailImage = toByteArray(thumbnail);
    UserImageEntity userImageEntity;
    try {
      userImageEntity = userImageService.get(userAccountId);
      userImageEntity.setFullSize(fullSizeImage);
      userImageEntity.setThumbnail(thumbnailImage);
    } catch (NotFoundException exception) {
      userImageEntity =
          UserImageEntity.builder()
              .userAccountId(userAccountId)
              .fullSize(fullSizeImage)
              .thumbnail(thumbnailImage)
              .build();
    }
    validate(userImageEntity);
    userImageService.save(userImageEntity);
  }

  public void handleDelete(final String userAccountId) {
    val entity = userImageService.get(userAccountId);
    userImageService.delete(entity);
  }

  private void validate(final UserImageEntity entity) {
    val errors = validator.validate(entity);
    if (!errors.isEmpty()) {
      throw new BadRequestException("There are some validation errors", errors);
    }
  }

  private BufferedImage toThumbnail(final BufferedImage originalImage) {
    val resizedImage =
        new BufferedImage(THUMBNAIL_SIZE, THUMBNAIL_SIZE, BufferedImage.TYPE_INT_RGB);
    resizedImage.getGraphics().drawImage(originalImage, 0, 0, THUMBNAIL_SIZE, THUMBNAIL_SIZE, null);
    return resizedImage;
  }

  private byte[] toByteArray(BufferedImage image) throws IOException {
    val outputStream = new ByteArrayOutputStream();
    ImageIO.write(image, IMAGE_FORMAT, outputStream);
    return outputStream.toByteArray();
  }
}
