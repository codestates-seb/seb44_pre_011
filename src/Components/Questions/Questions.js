import React from "react";
import style from "./Questions.module.css";
import { useNavigate } from "react-router";

const Questions = () => {
  const navigate = useNavigate();
  const ReadQuestion = () => {
    navigate("/questions/read");
  };

  return (
    <div id={style.container} onClick={ReadQuestion}>
      <h3>What is the fastest way to get the value of π?</h3>
      <div id={style.text}>
        'm looking for the fastest way to obtain the value of π, as a personal
        challenge. More specifically, I'm using ways that don't involve using
        #define constants like M_PI, or hard-coding the number in.
      </div>
      <div id={style.bottom}>
        <div id={style.taglist}>
          <div className={style.tag}>tag1</div>
          <div className={style.tag}>tag2</div>
          <div className={style.tag}>tag3</div>
        </div>
        <div id={style.userInfo}>
          <img src={process.env.PUBLIC_URL + "/img/test_img.jpg"} alt="test" />
          Username 2023/06/16 at 12:00
        </div>
      </div>
    </div>
  );
};

export default Questions;
