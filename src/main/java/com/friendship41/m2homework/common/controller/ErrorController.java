package com.friendship41.m2homework.common.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
  @GetMapping
  public String getErrorPage(final HttpServletRequest request, final Model model) {
    model.addAttribute("status", request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
    return "error";
  }
}
