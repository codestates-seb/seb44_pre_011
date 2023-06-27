import React, { useEffect, useState, useRef } from "react";
import Header from "../../Components/Header/Header";
import style from "./EditQuestionPage.module.css";
import Button from "@mui/material/Button";
import Editor from "../../Components/Editor/Editor";
import axios from "axios";
import { useRecoilValue } from "recoil";
import { questionIdState } from "../../store/auth";
import { useNavigate } from "react-router-dom";

const EditQuestionPage = () => {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [editTitle, setEditTitle] = useState("");
  const [editText, setEditText] = useState("");

  const quesId = useRecoilValue(questionIdState);

  const editTitleRef = useRef(null);
  const navigate = useNavigate();

  const handleTitleChange = (e) => {
    const editTitleValue = e.target.value;
    setEditTitle(editTitleValue);
  };

  useEffect(() => {
    axios({
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions/${quesId}`,
      method: "get",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
    })
      .then((response) => {
        setTitle(response.data.data.title);
        setContent(response.data.data.content);
      })
      .catch((err) => console.log(err));
  });

  const handleSavaEdit = () => {
    axios({
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions/${quesId}`,
      method: "PATCH",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
      data: {
        title: editTitle,
        content: editText,
      },
    })
      .then((res) => {
        console.log(res);
        navigate(`/questions/read?id=${quesId}`);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <>
      <Header />
      <div className={style.Allcontainer}>
        <div className={style.contents} id={style.first}>
          <h2>Edit your question</h2>
        </div>
        <div className={style.contents} id={style.title}>
          <div className={style.title_div1}>Title</div>
          <div className={style.title_div2}>
            Be specific and imagine you’re asking a question to another person.
          </div>
          <div id={style.title_div3}>
            <input
              type="text"
              defaultValue={title}
              className={style.title_div3_input}
              placeholder="e.g. Is there an R function for finding the index of an element in a vector? "
              ref={editTitleRef}
              onChange={handleTitleChange}
            />
          </div>
        </div>
        <div className={style.contents} id={style.problem}>
          <div className={style.title_div1}>
            What are the details of your problem?
          </div>
          <div className={style.title_div2}>
            Introduce the problem and expand on what you put in the title.
            Minimum 20 characters.
          </div>
          <div id={style.problem_div1}>
            <Editor text={content} setText={setEditText} />
          </div>
        </div>
        <div className={style.contents} id={style.tags}>
          <div className={style.title_div1}>tags</div>
          <div className={style.title_div2}>
            Add up to 5 tags to describe what your question is about. Start
            typing to see suggestions.
          </div>
          <div id={style.tags_div}>
            <input
              type="text"
              className={style.title_div3_input}
              placeholder="e.g. (angular regex django) "
            />
          </div>
        </div>
        <div className={style.contents}>
          <Button
            onClick={handleSavaEdit}
            variant="contained"
            sx={{
              fontSize: 12,
              height: "40px",
              marginLeft: "25px",
              marginBottom: "20px",
            }}
          >
            Save Edit
          </Button>
        </div>
      </div>
    </>
  );
};

export default EditQuestionPage;
