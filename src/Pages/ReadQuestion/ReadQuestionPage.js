import React from "react";
import { Link } from "react-router-dom";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import Footer from "../../Components/Footer/Footer";
import Button from "@mui/material/Button";
import style from "./ReadQuestionPage.module.css";

const ReadQuestionPage = () => {
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
              What is the fastest way to get the value of π?
            </h1>
            Asked 2023/06/16 at 12:00
          </div>
          <div id={style.contents}>
            <div>test</div>
            <div id={style.login}>
              To answer a question, you must either sign up for an account or
              post as a guest
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
