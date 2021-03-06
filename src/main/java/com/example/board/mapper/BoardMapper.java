package com.example.board.mapper;

import com.example.board.domain.BoardVO;

import java.util.List;

public interface BoardMapper {

  //    @Select("select * from tbl_board where bno > 0")
  public List<BoardVO> getList();

  public void insert(BoardVO board);

  public BoardVO read(Long bno);

  public int delete(Long bno);

  public int update(BoardVO board);
}