package com.example.board.service;

import com.example.board.domain.BoardVO;
import com.example.board.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

  @Setter(onMethod_ = @Autowired)
  private BoardMapper mapper;

  @Override
  public void write(BoardVO board) {
    log.info("write... " + board);
    mapper.insert(board);
  }

  @Override
  public BoardVO get(Long bno) {
    log.info("get... " + bno);
    return mapper.read(bno);
  }

  @Override
  public boolean modify(BoardVO board) {
    log.info("modify... " + board);
    return mapper.update(board) == 1;
  }

  @Override
  public boolean remove(Long bno) {
    log.info("remove... " + bno);
    return mapper.delete(bno) == 1;
  }

  @Override
  public List<BoardVO> getList() {
    log.info("getList... ");
    return mapper.getList();
  }
}