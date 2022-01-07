package com.example.board.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.board.service.BoardServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(controllers = BoardController.class)
@AutoConfigureMybatis
@AutoConfigureRestDocs
@Import(BoardServiceImpl.class)
class BoardControllerTest {

  @MockBean
  private BoardServiceImpl service;

  @Autowired
  private MockMvc mvc;

  @Autowired
  WebApplicationContext context;

  @BeforeEach
  public void setUp(WebApplicationContext wac,
      RestDocumentationContextProvider restDocumentation) { // (2)
    this.mvc = MockMvcBuilders.webAppContextSetup(wac)
        .apply(documentationConfiguration(restDocumentation)).build();
  }


  @DisplayName("게시판 제목들 가져오기")
  @Test
  public void boardGetTest() throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'+'0'0':'0'0", Locale.US);
    // https://github.com/FasterXML/jackson-databind/issues/1744
    // https://stackoverflow.com/questions/34723683/simpledateformat-ignores-xxx-if-timezone-is-set-to-utc
    df.setTimeZone(TimeZone.getTimeZone("GMT"));
    mapper.setDateFormat((df));

    ResultActions result = mvc.perform(get("/board/list")                 // (2)
        .contentType(MediaType.APPLICATION_JSON));

    result.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(
            MockMvcResultMatchers.content().json(
                mapper.writeValueAsString(service.getList())
            )
        )
        .andDo(print())
        .andDo(document("list-get",
            responseFields(
                fieldWithPath("[]").type(JsonFieldType.ARRAY).description("리스트"),
                fieldWithPath("[].author").type(JsonFieldType.STRING).description("작성자").optional(),
                fieldWithPath("[].content").type(JsonFieldType.STRING).description("내용").optional(),
                fieldWithPath("[].register_date").type(JsonFieldType.STRING).description("등록일시")
                    .optional(),
                fieldWithPath("[].mama").type(JsonFieldType.STRING).description("없는 컬럼").optional()
                    .optional()
            )));
  }
}