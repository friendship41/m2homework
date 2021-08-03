package com.friendship41.m2homework.main.controller.mvc;

import com.friendship41.m2homework.main.data.entity.M2Character;
import com.friendship41.m2homework.main.data.response.ResCharacter;
import com.friendship41.m2homework.main.mapper.ResCharacterMapper;
import com.friendship41.m2homework.main.service.M2CharacterService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/character")
@RequiredArgsConstructor
public class M2CharacterController {
  private final M2CharacterService m2CharacterService;
  private final ResCharacterMapper resCharacterMapper;

  @GetMapping("list")
  public String getCharacterList(final Model model) {
    int memberNo = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    List<M2Character> m2CharacterList = this.m2CharacterService.getM2CharacterList(memberNo);

    List<ResCharacter> resCharacterList = m2CharacterList.stream()
        .map(resCharacterMapper::toDto)
        .collect(Collectors.toList());

    model.addAttribute("resCharacterList", resCharacterList);

    return "main/characterList";
  }
}
