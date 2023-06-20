import React from "react";
import Header from "../../Components/Header/Header";
import Oauth from "../../Components/Oauth/Oauth";
import style from "./LoginPage.module.css"

const LoginPage = () => {
  return (
    <>
      <Header />
      <div className={style.AllContainer}>
        <img src={`${process.env.PUBLIC_URL}/img/StackOverFlow_logo.svg`} className={style.logoimg}/>
        <Oauth />
      </div>
    </>
  );
};

export default LoginPage;
