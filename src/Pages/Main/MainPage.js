import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import CustomPagination from "../../Components/Pagination/CustomPagination";
import Footer from "../../Components/Footer/Footer";
import Questions from "../../Components/Questions/Questions";
import Button from "@mui/material/Button";
import style from "./MainPage.module.css";
import { Link } from "react-router-dom";

const MainPage = () => {
  const [page, setPage] = useState(1);
  const offset = (page - 1) * 15;

  const [data, setData] = useState([]);

  useEffect(() => {
    axios({
      url: "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions",
      method: "get",
      headers: {
        "ngrok-skip-browser-warning": "skip",
        value: true,
      },
    })
      .then((response) => setData(response.data))
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
            {data.length} questions
          </div>
          {data.slice(offset, offset + 15).map((obj) => (
            <Questions
              key={obj.questionId}
              title={obj.title}
              content={obj.content}
              questionId={obj.questionId}
              createdAt={obj.createdAt}
            />
          ))}
        </div>
      </div>
      <div id={style.nav}>
        <CustomPagination
          array={data}
          currentPage={page}
          setCurrentPage={setPage}
        />
      </div>
      <div id={style.footer}>
        <Footer />
      </div>
    </div>
  );
};

export default MainPage;
