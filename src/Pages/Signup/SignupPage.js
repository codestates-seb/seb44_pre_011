import React, { useState } from "react";
import Header from "../../Components/Header/Header";
import Style from "./SignupPage.module.css";
import Button from "@mui/material/Button";
import Oauth from "../../Components/Oauth/Oauth";
import axios from "axios";

const SignupPage = () => {
  return (
    <>
      <Header />
      <div className={Style.signUpContainer}>
        <div className={Style.HeadLine}>
          <HeadLine />
        </div>
        <div className={Style.SignUpForm}>
          <Oauth />
          <SignUpForm />
          <p>
            Already have an account?
            <a href="http://localhost:3000/login">Log in</a>
          </p>
          <p>
            Are you an employer?
            <a href="https://talent.stackoverflow.com/users/login">
              Sign up on Talent
            </a>
          </p>
        </div>
      </div>
    </>
  );
};

export default SignupPage;

const HeadLine = () => {
  return (
    <div className={Style.HeadLineContainer}>
      <h2 className={Style.HeadLineTitle}>Join the Stack Overflow community</h2>
      <div className={Style.listItem}>
        <img
          src={`${process.env.PUBLIC_URL}/img/headline_icon1.svg`}
          alt="headline_icon1"
        />
        Get unstuck — ask a question
      </div>
      <div className={Style.listItem}>
        <img
          src={`${process.env.PUBLIC_URL}/img/headline_icon2.svg`}
          alt="headline_icon2"
        />
        Unlock new privileges like voting and commenting
      </div>
      <div className={Style.listItem}>
        <img
          src={`${process.env.PUBLIC_URL}/img/headline_icon3.svg`}
          alt="headline_icon3"
        />
        Save your favorite questions, answers, watch tags, and more
      </div>
      <div className={Style.listItem}>
        <img
          src={`${process.env.PUBLIC_URL}/img/headline_icon4.svg`}
          alt="headline_icon4"
        />
        Earn reputation and badges
      </div>
      <div className={Style.Link}>
        Collaborate and share knowledge with a private group for FREE.
        <a
          href="https://stackoverflow.co/teams/?utm_source=so-owned&utm_medium=product&utm_campaign=free-50&utm_content=public-sign-up"
          target="_blank"
          rel="noreferrer"
        >
          Get Stack Overflow for Teams free for up to 50 users.
        </a>
      </div>
    </div>
  );
};

const SignUpForm = () => {
  const [memberData, setMemberData] = useState({
    email: "",
    password: "",
    displayName: "",
  });
  const [emailErrMsg, setEmailErrMsg] = useState("");
  const [pwdErrMsg, setPwdErrMsg] = useState("");
  const [nameErrMsg, setNameErrMsg] = useState("");

  const regExpPwd =
    /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;

  // email 값 설정 및 유효성검사
  const handleEmailValue = (e) => {
    setMemberData({ ...memberData, email: e.target.value });
  };
  const handlePwdValue = (e) => {
    setMemberData({ ...memberData, password: e.target.value });
  };
  const handleNameValue = (e) => {
    setMemberData({ ...memberData, displayName: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (memberData.email === "") {
      setEmailErrMsg("이메일을 입력해주세요.");
    } else {
      setEmailErrMsg("");
    }

    if (memberData.password === "") {
      setPwdErrMsg("비밀번호를 입력해주세요.");
    } else if (regExpPwd.test(memberData.password)) {
      setPwdErrMsg("");
    } else {
      setPwdErrMsg(
        "최소 8자, 하나의 이상의 대소문자, 숫자, 특수문자를 포함해야 합니다."
      );
    }

    if (memberData.displayName === "") {
      setNameErrMsg("닉네임을 입력해주세요.");
    } else {
      setNameErrMsg("");
    }

    if (!emailErrMsg && !nameErrMsg && !pwdErrMsg) {
      axios({
        url: "https://b843-61-77-96-105.ngrok-free.app/members",
        method: "post",
        data: {
          ...memberData,
        },
      })
        .then((res) => console.log(res))
        .catch((err) => {
          console.log(err);
          setEmailErrMsg("이미 사용중인 이메일 입니다.");
        });
    }
  };

  return (
    <div className={Style.formContainer}>
      <form className={Style.form} onSubmit={handleSubmit}>
        <label className={Style.title}>
          Display Name
          <input
            className={Style.input}
            type="text"
            onChange={handleNameValue}
          />
          <div className={Style.errMsg}>{nameErrMsg}</div>
        </label>
        <label className={Style.title}>
          Email
          <input
            className={Style.input}
            type="email"
            onChange={handleEmailValue}
          />
          <div className={Style.errMsg}>{emailErrMsg}</div>
        </label>
        <label className={Style.title}>
          Password
          <input
            className={Style.input}
            type="password"
            onChange={handlePwdValue}
          />
          <div className={Style.errMsg}>{pwdErrMsg}</div>
        </label>

        <div className={Style.checkBox}>
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>

          <p>
            <input type="checkbox" />
            Opt-in to receive occasional product updates, user research
            invitations, company announcements, and digests.
          </p>
        </div>
        <Button
          className={Style.button}
          type="submit"
          variant="contained"
          sx={{ fontSize: 14, width: "100%", height: "40px" }}
        >
          Sign Up
        </Button>
        <p className={Style.note}>
          By clicking “Sign up”, you agree to our{" "}
          <a
            href="https://stackoverflow.com/legal/terms-of-service/public"
            target="_blank"
            rel="noreferrer"
          >
            {" "}
            terms of
            <br />
            service
          </a>{" "}
          and acknowledge that you have read and understand our{" "}
          <a
            href="https://stackoverflow.com/legal/privacy-policy"
            target="_blank"
            rel="noreferrer"
          >
            {" "}
            privacy policy
          </a>{" "}
          and{" "}
          <a
            href="https://stackoverflow.com/conduct"
            target="_blank"
            rel="noreferrer"
          >
            code of conduct.
          </a>
        </p>
      </form>
    </div>
  );
};
