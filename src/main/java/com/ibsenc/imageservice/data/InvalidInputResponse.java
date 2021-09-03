package com.ibsenc.imageservice.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvalidInputResponse {

  private String error;
}
