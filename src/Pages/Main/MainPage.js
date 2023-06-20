import React from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import Footer from "../../Components/Footer/Footer";
import Questions from "../../Components/Questions/Questions";
import Button from "@mui/material/Button";
import style from "./MainPage.module.css";

const MainPage = () => {
  const [page, setPage] = useState(1);
  const offset = (page - 1) * 15;

  const array = Array(50).fill();
  const numPages = Math.ceil(array.length / 15);

  return (
    <div>
      <Header />
      <div id={style.main}>
        <div id={style.asdie}>
          <Aside />
        </div>
        <div id={style.question}>
          <div id={style.questionHead}>
            <div id={style.questionHeadTitle}>
              <h1>All Questions</h1>
              <Link to="/questions/ask">
                <Button
                  variant="contained"
                  sx={{
                    fontSize: 13,
                    width: "140px",
                    height: "50px",
                    marginTop: "10px",
                    marginLeft: "10px",
                  }}
                >
                  Ask Question
                </Button>
              </Link>
            </div>
            {array.length} questions
          </div>
          {array.slice(offset, offset + 15).map((index) => (
            <Questions key={index} />
          ))}
        </div>
      </div>
      <div id={style.nav}>
        <button
          className={style.button}
          onClick={() => setPage(page - 1)}
          disabled={page === 1}
        >
          &lt;
        </button>
        {Array(numPages)
          .fill()
          .map((_, i) => (
            <button
              className={page === i + 1 ? style.buttonselect : style.button}
              key={i + 1}
              onClick={() => setPage(i + 1)}
            >
              {i + 1}
            </button>
          ))}
        <button
          className={style.button}
          onClick={() => setPage(page + 1)}
          disabled={page === numPages}
        >
          &gt;
        </button>
      </div>
      <div id={style.footer}>
        <Footer />
      </div>
    </div>
  );
};

export default MainPage;
