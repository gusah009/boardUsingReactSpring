package com.example.board.controller;

import com.example.board.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BoardController.class)
@AutoConfigureMybatis
@Import(BoardServiceImpl.class)
class BoardControllerTest {

  @Autowired
  private BoardServiceImpl service;

  @Autowired
  private MockMvc mvc;

  @Test
  public void boardGetTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'0'0':'0'0", Locale.US);
    // https://github.com/FasterXML/jackson-databind/issues/1744
    // https://stackoverflow.com/questions/34723683/simpledateformat-ignores-xxx-if-timezone-is-set-to-utc
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    mapper.setDateFormat((df));

    mvc.perform(MockMvcRequestBuilders.get("/board/list"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().json(
                mapper.writeValueAsString((service.getList()))
            )
        );
  }

}