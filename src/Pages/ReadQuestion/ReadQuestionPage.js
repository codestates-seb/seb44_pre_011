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
            <div>
              Chrome/Firefox is not displaying maths equations and some websites
              correctly as follows.
              <br />
              enter image description here
              <br />
              enter image description here
              <br />
              Is it the issue of the site or the browser or some plugins ?
              <br />
              Thanks and Regards Karthy
              <br />
              Tried different plugins and different browsers
              <div id={style.taglist}>
                <div className={style.tag}>tag1</div>
                <div className={style.tag}>tag2</div>
                <div className={style.tag}>tag3</div>
              </div>
            </div>
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
