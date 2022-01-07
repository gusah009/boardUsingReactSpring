package com.example.board.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {

  private Long id;
  private String title;
  private String content;
  private String author;
  private Date register_date;
  private Date updateDate;
}