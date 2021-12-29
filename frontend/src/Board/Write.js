import React, { useState } from "react";
import axios from "axios";

import "./Board.css";

function Write() {
  const [Title, SetTitle] = useState("");
  const [Content, SetContent] = useState("");

  const titleHandler = (e) => {
    e.preventDefault();
    SetTitle(e.target.value);
  };

  const contentHandler = (e) => {
    e.preventDefault();
    SetContent(e.target.value);
  };

  const submitHandler = (e) => {
    e.preventDefault();
    // state에 저장한 값을 가져옵니다.
    console.log(Title);
    console.log(Content);

    let body = {
      title: Title,
      content: Content,
    };

    axios.post("/board/write", body).then((res) => console.log(res));
  };

  return (
    <>
      <div className="form-wrapper">
        <form onSubmit={submitHandler}>
          <input
            className="title-input"
            type="text"
            placeholder="제목"
            value={Title}
            onChange={titleHandler}
          />
          <textarea
            className="text-area"
            placeholder="내용"
            value={Content}
            onChange={contentHandler}
          ></textarea>
        </form>
      </div>
      <button className="submit-button" type="submit">입력</button>
    </>
  );
}

export default Write;
