package com.maple.useraccount.api.v1.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountRequest {

  private String firstName;
  private String lastName;
  private String dateOfBirth;
}
