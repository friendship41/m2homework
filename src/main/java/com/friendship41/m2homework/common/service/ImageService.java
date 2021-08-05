package com.friendship41.m2homework.common.service;

import com.friendship41.m2homework.common.data.entity.ImageData;
import com.friendship41.m2homework.common.data.type.AccessScope;
import com.friendship41.m2homework.common.exception.ImageAccessNotAllowedException;
import com.friendship41.m2homework.common.exception.ImageNotFoundException;
import com.friendship41.m2homework.common.exception.NotValidImageContentTypeException;
import com.friendship41.m2homework.common.repository.ImageDataRepository;
import com.friendship41.m2homework.common.util.AccessScopeUtil;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageService {
  private final ImageDataRepository imageDataRepository;

  private static final SimpleDateFormat SIMPLE_DATE_FORMAT_YYYYMMDD = new SimpleDateFormat("yyyyMMdd");

  @Value("${spring.profiles.active}")
  private String activeProfile;

  @Autowired
  public ImageService(final ImageDataRepository imageDataRepository) {
    this.imageDataRepository = imageDataRepository;
  }

  public List<ImageData> saveImage(final List<MultipartFile> multipartFileList, final String accessScopes) {
    if (multipartFileList == null || multipartFileList.isEmpty()) {
      return new ArrayList<>();
    }
    return multipartFileList.stream()
        .map(multipartFile -> this.saveImage(multipartFile, accessScopes))
        .collect(Collectors.toList());
  }

  public ImageData saveImage(final MultipartFile multipartFile, final String accessScopes) {
    if (!isValidImageFile(multipartFile)) {
      return null;
    }

    String imageLocation;
    if (activeProfile.equals("release")) {
      imageLocation = "/m2";
    } else {
      imageLocation = "D:/m2";
    }
    imageLocation += "/images/" + SIMPLE_DATE_FORMAT_YYYYMMDD.format(new Date());

    File file = new File(imageLocation);
    if (!file.exists()) {
      file.mkdirs();
    }

    String contentType = multipartFile.getContentType();
    String ext;
    assert contentType != null;
    if (contentType.contains("image/jpeg") || contentType.contains("image/jpg")) {
      ext = "jpg";
    } else if (contentType.contains("image/png")) {
      ext = "png";
    } else if (contentType.contains("image/gif")) {
      ext = "gif";
    } else {
      throw new NotValidImageContentTypeException(multipartFile.getOriginalFilename(), multipartFile.getContentType());
    }

    String storedFileName = System.nanoTime() + "." + ext;

    file = new File(imageLocation + "/" + storedFileName);
    try {
      multipartFile.transferTo(file);
    } catch (IOException e) {
      log.error("fail to save file to disk, multipartFile: {}", multipartFile, e);
      return null;
    }

    ImageData imageData = ImageData.builder()
        .imageLocation(imageLocation)
        .originFileName(multipartFile.getOriginalFilename())
        .storedFileName(storedFileName)
        .ext(ext)
        .size(multipartFile.getSize())
        .accessScopes(accessScopes)
        .build();

    return this.imageDataRepository.save(imageData);
  }

  public Resource getAnyScopeImage(final int imageNo) {
    ImageData imageData = this.imageDataRepository.findById(imageNo)
        .orElseThrow(() -> new ImageNotFoundException(imageNo));
    if (!AccessScopeUtil.hasScope(imageData.getAccessScopes(), AccessScope.ANY)) {
      throw new ImageAccessNotAllowedException(imageNo, AccessScope.ANY);
    }
    Resource resource = new FileSystemResource(imageData.getImageLocation() + "/" + imageData.getStoredFileName());

    if (resource.exists() && resource.isReadable()) {
      return resource;
    }

    throw new ImageNotFoundException(imageNo, imageData.getImageLocation() + "/" + imageData.getStoredFileName());
  }

  private boolean isValidImageFile(final MultipartFile multipartFile) {
    if (multipartFile == null || multipartFile.isEmpty()) {
      log.warn("no image file to save, mulitipartFile: {}", multipartFile);
      return false;
    } else if (multipartFile.getContentType() == null || multipartFile.getContentType().isEmpty()) {
      log.warn("no image file ext, contentType: {}", multipartFile.getContentType());
      return false;
    }
    return true;
  }
}
