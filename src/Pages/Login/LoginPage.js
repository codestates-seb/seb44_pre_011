import React from "react";
import Header from "../../Components/Header/Header";
import Oauth from "../../Components/Oauth/Oauth";
import style from "./LoginPage.module.css"

const LoginPage = () => {
  return (
    <>
      <Header />
      <div className={style.AllContainer}>
        <div>
          <img src={`${process.env.PUBLIC_URL}/img/StackOverFlow_logo.svg`}></img>
        </div>
        <div>
          <Oauth/>
        </div>
        <div>
          3
        </div>
        <div>
          4
        </div>
      </div>
    </>
  );
};

export default LoginPage;
