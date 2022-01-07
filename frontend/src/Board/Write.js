import React, { useState } from "react";
import axios from "axios";

import "./Board.css";

function Write() {
  const [Title, SetTitle] = useState("");
  const [Content, SetContent] = useState("");
  const [Author, SetAuthor] = useState("");

  const titleHandler = (e) => {
    e.preventDefault();
    console.log(Title);
    SetTitle(e.target.value);
  };

  const contentHandler = (e) => {
    e.preventDefault();
    console.log(Content);
    SetContent(e.target.value);
  };

  const authorHandler = (e) => {
    e.preventDefault();
    console.log(Author);
    SetAuthor(e.target.value);
  };

  const submitHandler = (e) => {
    e.preventDefault();
    // state에 저장한 값을 가져옵니다.
    console.log(Title);
    console.log(Content);
    console.log(Author);

    let body = {
      title: Title,
      content: Content,
      author: Author
    };

    axios.post("/board/write", body).then((res) => console.log(res));
  };

  return (
    <>
      <div className="form-wrapper">
        <input
          className="title-input"
          type="text"
          placeholder="제목"
          value={Title}
          onChange={titleHandler}
        />
        <input
          className="title-input"
          type="text"
          placeholder="작성자"
          value={Author}
          onChange={authorHandler}
        />
        <textarea
          className="text-area"
          placeholder="내용"
          value={Content}
          onChange={contentHandler}
        ></textarea>
        <button className="submit-button" type="submit" onClick={submitHandler}>입력</button>
      </div>
    </>
  );
}

export default Write;
