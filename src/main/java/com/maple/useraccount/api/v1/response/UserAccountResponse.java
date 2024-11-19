package com.maple.useraccount.api.v1.response;

import com.alviss.commons.api.model.Meta;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountResponse {

  private String id;
  private UserAccountData data;

  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserAccountData {
    private String organizationId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
  }
}
