import React from "react";
import Header from "../../Components/Header/Header";
import Oauth from "../../Components/Oauth/Oauth";
import style from "./LoginPage.module.css";
import Button from "@mui/material/Button";
import { Link } from "react-router-dom";

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
          <SignUpForm />
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

const SignUpForm = () => {
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className={style.formContainer}>
      <form className={style.form} onSubmit={handleSubmit}>
        <label className={style.title}>
          Email
          <input className={style.input} type="email" />
        </label>
        <label className={style.title}>
          Password
          <input className={style.input} type="password" />
        </label>
        <Button
          className={style.button}
          variant="contained"
          sx={{ fontSize: 14, width: "100%", height: "40px" }}
        >
          Log in
        </Button>
      </form>
    </div>
  );
};
