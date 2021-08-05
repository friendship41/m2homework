package com.friendship41.m2homework.common.controller;

import com.friendship41.m2homework.common.service.ImageService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/open/api")
@RequiredArgsConstructor
@Slf4j
public class OpenApiController {
  private final ImageService imageService;

  @GetMapping("image/any/{imageNo}")
  public ResponseEntity<?> getAnyScopeImage(@PathVariable("imageNo") final int imageNo,
      final HttpServletRequest request) throws IOException {
    Resource resource = this.imageService.getAnyScopeImage(imageNo);
    String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(contentType))
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + resource.getFilename() + "\"")
        .body(resource);
  }
}
