import React, { useState } from "react";
import Header from "../../Components/Header/Header";
import Oauth from "../../Components/Oauth/Oauth";
import style from "./LoginPage.module.css";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import { useNavigate } from "react-router";
import { useSetRecoilState } from "recoil";
import { loginState } from "../../store/auth";

const LoginPage = () => {
  return (
    <>
      <Header />
      <div className={style.AllContainer}>
        <div className={style.Container}>
          <img
            src={`${process.env.PUBLIC_URL}/img/StackOverFlow_logo.svg`}
            className={style.logoimg}
            alt="StackOverFlow_logo"
          ></img>
        </div>
        <div className={style.Container}>
          <Oauth value="Log in with Google" />
        </div>
        <div className={style.Container}>
          <LoginForm />
        </div>
        <div className={style.Container}>
          <p>Don’t have an account? &nbsp;</p>
          <Link to="/signup">Sign up</Link>
        </div>
      </div>
    </>
  );
};

export default LoginPage;

const LoginForm = () => {
  const [loginInfo, setLoginInfo] = useState({
    username: "",
    password: "",
  });
  const [errorMessage, setErrorMessage] = useState("");
  const setLoginState = useSetRecoilState(loginState);
  const navigate = useNavigate();

  const handleInputValue = (key) => (e) => {
    setLoginInfo({ ...loginInfo, [key]: e.target.value });
  };

  const loginRequestHandler = () => {
    if (!loginInfo.username || !loginInfo.password) {
      setErrorMessage("아이디와 비밀번호를 입력하세요.");
      return;
    }

    return axios
      .post(
        "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/auth/login",
        loginInfo
      )
      .then((res) => {
        console.log(res);

        if (res.status === 200) {
          const authHeader = res.headers.authorization;
          const token = authHeader.split(" ")[1];
          // axios.defaults.headers.common["authorization"] = `Bearer ${token}`;
          const memberId = res.headers.memberid;

          console.log(memberId);
          sessionStorage.setItem("token", token);
          sessionStorage.setItem("id", memberId);
          setLoginState(true);
          setErrorMessage("");
          navigate("/questions");
        }
      })
      .catch((err) => {
        console.log(err.response.data);
        setErrorMessage("로그인에 실패했습니다.");
      });
  };

  return (
    <div className={style.formContainer}>
      <form className={style.form} onSubmit={(e) => e.preventDefault()}>
        <label className={style.title}>
          Email
          <input
            className={style.input}
            type="email"
            onChange={handleInputValue("username")}
          />
        </label>
        <label className={style.title}>
          Password
          <input
            className={style.input}
            type="password"
            onChange={handleInputValue("password")}
          />
        </label>
        <Button
          className={style.button}
          variant="contained"
          sx={{ fontSize: 14, width: "100%", height: "40px" }}
          onClick={loginRequestHandler}
        >
          Log in
        </Button>
        {errorMessage ? (
          <div id="alert-message" data-testid="alert-message">
            {errorMessage}
          </div>
        ) : (
          ""
        )}
      </form>
    </div>
  );
};
