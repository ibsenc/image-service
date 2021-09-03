package com.ibsenc.imageservice.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadResponse {

  @JsonProperty("image_id")
  private String imageId;
}
