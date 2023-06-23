import Button from "@mui/material/Button";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import Footer from "../../Components/Footer/Footer";
import style from "./ReadQuestionPage.module.css";

const Login = () => {
  return (
    <>
      <div id={style.login}>
        To answer a question, you must either sign up for an account
      </div>
      <div>
        <span className={style.buttonContainer}>
          <Link to="/login">
            <Button
              variant="contained"
              sx={{
                fontSize: 20,
                width: "200px",
                height: "50px",
                marginTop: "10px",
                backgroundColor: "#e3ecf3",
                color: "#1976D2",
                ":hover": {
                  color: "#e3ecf3",
                },
              }}
            >
              Login
            </Button>
          </Link>
          <Link to="/signup">
            <Button
              variant="contained"
              sx={{
                fontSize: 20,
                width: "200px",
                height: "50px",
                marginTop: "10px",
                marginLeft: "10px",
              }}
            >
              Sign Up
            </Button>
          </Link>
        </span>
      </div>
    </>
  );
};

const Answer = () => {
  const [text, setText] = useState("");
  const Submit = () => {
    console.log(text);
    axios({
      url: "",
      method: "post",
      data: {},
    })
      .then((res) => {
        console.log(res);
        window.location.href = "/";
      })
      .catch((err) => {
        console.log(err);
      });
  };
  return (
    <div id={style.answer}>
      <span id={style.answertitle}>Your Answer</span>
      <textarea
        id={style.textarea}
        value={text}
        onChange={(e) => setText(e.target.value)}
      />
      <Link to="/questions/read">
        <Button
          variant="contained"
          sx={{
            fontSize: 12,
            width: "165px",
            height: "40px",
            marginTop: "20px",
          }}
          onClick={Submit}
        >
          Post Your Answer
        </Button>
      </Link>
    </div>
  );
};

const ReadQuestionPage = () => {
  const [login, setLogin] = useState(true);
  const [data, setData] = useState({ createdAt: "00000000000" });

  useEffect(() => {
    axios({
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions/
      ${document.location.search.slice(4)}`,
      method: "get",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
    })
      .then((response) => setData(response.data.data))
      .catch((err) => console.log(err));
  }, []);

  return (
    <div>
      <Header />
      <div id={style.main}>
        <div id={style.asdie}>
          <Aside />
        </div>
        <div id={style.question}>
          <div id={style.title}>
            <h1 id={style.h1}>
              {data.title}
              <Link to="/questions/ask">
                <Button
                  variant="contained"
                  sx={{
                    fontSize: 13,
                    width: "140px",
                    height: "50px",
                    marginTop: "10px",
                    marginLeft: "10px",
                    float: "right",
                  }}
                >
                  Ask Question
                </Button>
              </Link>
            </h1>
            Asked {data.createdAt.slice(0, 10)} at{" "}
            {data.createdAt.slice(11, 16)}
          </div>
          <div id={style.contents}>
            <div>
              {data.content}
              <div id={style.taglist}>
                <div className={style.tag}>tag1</div>
                <div className={style.tag}>tag2</div>
                <div className={style.tag}>tag3</div>
              </div>
            </div>
            <div id={style.Answer}>Answer</div>
            <div id={style.AnswerText}>asdasdasd</div>
            {login ? <Answer /> : <Login />}
          </div>
        </div>
      </div>

      <div id={style.footer}>
        <Footer />
      </div>
    </div>
  );
};

export default ReadQuestionPage;
