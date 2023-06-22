import React from "react";
import style from "./Questions.module.css";
import { useNavigate } from "react-router";

const Questions = ({ questionId, title, content, createdAt }) => {
  const navigate = useNavigate();
  const ReadQuestios = () => {
    navigate(`/questions/read?id=${questionId}`);
  };
  return (
    <div id={style.container}>
      <h3 onClick={ReadQuestios}>{title}</h3>
      <div id={style.text}>{content}</div>
      <div id={style.bottom}>
        <div id={style.taglist}>
          <div className={style.tag}>tag1</div>
          <div className={style.tag}>tag2</div>
          <div className={style.tag}>tag3</div>
        </div>
        <div id={style.userInfo}>
          <img
            src={process.env.PUBLIC_URL + "/img/test_img.jpg"}
            alt={questionId}
          />
          Username {createdAt.slice(0, 10)} at {createdAt.slice(11, 16)}
        </div>
      </div>
    </div>
  );
};

export default Questions;
