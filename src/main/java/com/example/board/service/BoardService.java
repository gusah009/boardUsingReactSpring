package com.example.board.service;

import com.example.board.domain.BoardVO;

import java.util.List;

public interface BoardService {

  public void write(BoardVO board);

  public BoardVO get(Long bno);

  public boolean modify(BoardVO board);

  public boolean remove(Long bno);

  public List<BoardVO> getList();
}