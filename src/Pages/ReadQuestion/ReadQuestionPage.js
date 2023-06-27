import Button from "@mui/material/Button";
import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import Editor from "../../Components/Editor/Editor";
import Footer from "../../Components/Footer/Footer";
import style from "./ReadQuestionPage.module.css";
import { useRecoilValue } from "recoil";
import { loginState, userDataState } from "../../store/auth";

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

const Answer = ({ userdata }) => {
  const [text, setText] = useState("");

  const Submit = () => {
    console.log(text);
    if (text.length > 20) {
      axios({
        url: "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/answers",
        method: "post",
        data: {
          memberId: userdata.memberId,
          questionId: document.location.search.slice(4),
          content: text,
        },
      })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err);
        });
      setText("");
      window.location.reload();
    }
  };
  return (
    <div id={style.answer}>
      <span id={style.answertitle}>Your Answer</span>
      <Editor text={text} setText={setText} />
      <Link to={`/questions/read?id=${document.location.search.slice(4)}`}>
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
  const userdata = useRecoilValue(userDataState);
  const isLogin = useRecoilValue(loginState);
  const [data, setData] = useState({ createdAt: "00000000000" });
  const [answer, setAnswer] = useState([]);
  const [answertext, setAnswertext] = useState("");
  const [answerid, setAnswerid] = useState();

  const EditAnswer = (answerid, text) => {
    setAnswertext(text);
    setAnswerid(answerid);
  };

  const EditSubmit = (answerid, answertext) => {
    axios({
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/answers/${answerid}`,
      method: "PATCH",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
      data: {
        content: answertext,
      },
    })
      .then((response) => setData(response.data.data))
      .catch((err) => console.log(err));
    setAnswertext("");
    setAnswerid();
    window.location.reload();
  };

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

    axios({
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/answers/question/
      ${document.location.search.slice(4)}`,
      method: "get",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
    })
      .then((response) => setAnswer(response.data))
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

            {answer.map((obj) => (
              <div id={style.AnswerText}>
                {answerid === obj.answerId ? (
                  <div>
                    <Editor text={answertext} setText={setAnswertext} />
                    <Link
                      to={`/questions/read?id=${document.location.search.slice(
                        4
                      )}`}
                    >
                      <Button
                        variant="contained"
                        sx={{
                          fontSize: 12,
                          width: "165px",
                          height: "40px",
                          marginTop: "20px",
                        }}
                        onClick={() => EditSubmit(obj.answerId, answertext)}
                      >
                        Edit Your Answer
                      </Button>
                    </Link>
                  </div>
                ) : (
                  <div dangerouslySetInnerHTML={{ __html: obj.content }}></div>
                )}
                <div id={style.AnswerFooter}>
                  <button
                    id={style.EditButton}
                    onClick={
                      userdata.memberId === obj.memberId
                        ? () => EditAnswer(obj.answerId, obj.content)
                        : undefined
                    }
                  >
                    {answerid ? "" : "Edit"}
                  </button>
                  <div id={style.answerUserInfo}>
                    <img
                      src={process.env.PUBLIC_URL + "/img/test_img.jpg"}
                      alt="img"
                    />
                    {obj.displayName}
                  </div>
                </div>
              </div>
            ))}

            {isLogin ? <Answer userdata={userdata} /> : <Login />}
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
