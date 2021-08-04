package com.friendship41.m2homework.main.controller.rest;

import com.friendship41.m2homework.common.data.entity.ImageData;
import com.friendship41.m2homework.common.service.ImageService;
import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.request.ReqCharacter;
import com.friendship41.m2homework.main.mapper.ReqCharacterMapper;
import com.friendship41.m2homework.main.mapper.ResCharacterMapper;
import com.friendship41.m2homework.main.service.M2CharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/rest/character")
@RequiredArgsConstructor
public class M2CharacterRestController {
  private final M2CharacterService m2CharacterService;
  private final ImageService imageService;

  private final ReqCharacterMapper reqCharacterMapper;
  private final ResCharacterMapper resCharacterMapper;

  @GetMapping("job/list")
  public Object getJobKorNameList() {
    return this.m2CharacterService.getJobTypeList();
  }

  @GetMapping("list")
  public Object getM2CharacterList(
      @RequestParam(name = "isMain", required = false, defaultValue = "false") boolean isMain) {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return this.m2CharacterService.getM2CharacterIsMainList(memberNo, isMain).stream()
        .map(resCharacterMapper::toDto)
        .collect(Collectors.toList());
  }

  @PostMapping
  public Object postM2Character(@RequestBody ReqCharacter reqCharacter) {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    M2Character m2Character = reqCharacterMapper.toDto(reqCharacter);
    m2Character.setMemberNo(memberNo);
    return this.m2CharacterService.insertCharacter(m2Character);
  }

  @PostMapping("image")
  public Object postM2CharacterImage(final MultipartHttpServletRequest request) {
    List<ImageData> imageDataList = this.imageService.saveImage(request.getFiles("characterImage"));
    int characterNo = Integer.parseInt(request.getParameter("characterNo"));

    return this.m2CharacterService.updateCharacterImage(characterNo, imageDataList);
  }
}
