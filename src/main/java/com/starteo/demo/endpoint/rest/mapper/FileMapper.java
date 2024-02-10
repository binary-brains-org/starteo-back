package com.starteo.demo.endpoint.rest.mapper;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component
public class FileMapper {
  public String encoodeToBase64(byte[] image) {
    return Base64.getEncoder().encodeToString(image);
  }
}
