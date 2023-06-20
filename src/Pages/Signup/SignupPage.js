import React from "react";
import Header from "../../Components/Header/Header";
import Style from "./SignupPage.module.css";
import Button from "@mui/material/Button";
import Oauth from "../../Components/Oauth/Oauth";

const SignupPage = () => {
  return (
    <>
      <Header />
      <div id={Style.signUpContainer}>
        <HeadLine />
        <div id={Style.form}>
          <Oauth />
          <SignUpForm />
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
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className={Style.formContainer}>
      <form className={Style.form} onSubmit={handleSubmit}>
        <label className={Style.title}>
          Display Name
          <input className={Style.input} type="text" />
        </label>
        <label className={Style.title}>
          Email
          <input className={Style.input} type="email" />
        </label>
        <label className={Style.title}>
          Password
          <input className={Style.input} type="password" />
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
          variant="contained"
          sx={{ fontSize: 14, width: "100%", height: "40px" }}
        >
          Sign Up
        </Button>
        <p className={Style.note}>
          By clicking “Sign up”, you agree to our
          <a
            href="https://stackoverflow.com/legal/terms-of-service/public"
            target="_blank"
            rel="noreferrer"
          >
            terms of
            <br />
            service
          </a>
          and acknowledge that you have read and understand our
          <a
            href="https://stackoverflow.com/legal/privacy-policy"
            target="_blank"
            rel="noreferrer"
          >
            privacy policy
          </a>
          and
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
