package com.example.board.controller;


import com.example.board.domain.BoardVO;
import com.example.board.service.BoardServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping(path = "/board")
@AllArgsConstructor
public class BoardController {
  private BoardServiceImpl service;

  @GetMapping("/list")
  public List<BoardVO> list() {
    log.info("list");
    return service.getList();
  }

}
