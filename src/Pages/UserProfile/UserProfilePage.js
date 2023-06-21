import React, { useEffect, useState } from "react";
import styles from "./UserProfilePage.module.css";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import Aside from "../../Components/Aside/Aside";
import EmailIcon from "@mui/icons-material/Email";
import EditIcon from "@mui/icons-material/Edit";
import { Button, Typography } from "@mui/material";
import Questions from "../../Components/Questions/Questions";
import CustomPagination from "../../Components/Pagination/CustomPagination";
// 테스트용 유저 정보
const userInfo = {
  memberId: 1,
  displayName: "FASTFOX",
  email: "dhtmdcks1325@gmail.com",
  userImage:
    "https://lh3.googleusercontent.com/a/AAcHTtelXBjmABo2mqEjXfeLRF7MXkN4kyZNZ7lEctOHag=k-s256",
};
const UserProfilePage = ({ memberId }) => {
  const [nav, setNav] = useState("Questions");
  const [list, setList] = useState([]); // questions냐 answers냐에 따라 list가 변경. => 이 list를 가지고 페이지에 출력
  const [currentPage, setCurrentPage] = useState(1);
  // 테스트용 array
  const questions = Array(50).fill();
  const answers = Array(100).fill();
  // 데이터 동기화가 되면 map 내부에서 return되는 컴포넌트의 props는 수정 필요.
  const makeList = (array) => {
    const startIndex = (currentPage - 1) * 15;
    return array
      .slice(startIndex, startIndex + 15)
      .map((question, idx) => <Questions key={`question${idx}`} />);
  };
  useEffect(() => {
    if (nav === "Questions") {
      setList(makeList(questions));
    } else {
      setList(makeList(answers));
    }
  }, [currentPage]);
  useEffect(() => {
    setCurrentPage(1);
  }, [nav]);
  // fetch를 통해서 all questions와 all answers를 받아오는 useEffect
  useEffect(() => {
    (async () => {})();
  }, []);
  return (
    <div className={styles.flex_column}>
      <Header />
      <div className={`${styles.flex_row} ${styles.justify_center}`}>
        <div id={styles.aside}>
          <Aside />
        </div>
        <div id={styles.content} className={styles.alignItems_center}>
          <div className={`${styles.flex_row} `}>
            <img
              alt="user"
              src={userInfo.userImage}
              id={styles.user_image}
              className={styles.margin_right}
            />
            <div
              id={styles.userInfo}
              className={`${styles.flex_column} ${styles.justify_center}`}
            >
              <span id={styles.displayName}>{userInfo.displayName}</span>
              <span
                id={styles.user_info_sub}
                className={`${styles.flex_row} ${styles.alignItems_center} ${styles.margin_top}`}
              >
                <EmailIcon
                  sx={{ color: "#9298a1", fontSize: "18px" }}
                  className={styles.margin_right}
                />
                {userInfo.email}
              </span>
            </div>
            {memberId === userInfo.memberId ? (
              <div className={`${styles.flex_row}`}>
                <Button
                  sx={{
                    textTransform: "none",
                    height: "40px",
                    border: "1px solid #6a737c",
                    color: "#6a737c",
                  }}
                  href={`/users/edit/${userInfo.memberId}`}
                >
                  <EditIcon />
                  Edit profile
                </Button>
              </div>
            ) : (
              <></>
            )}
          </div>
          <div className={`${styles.flex_row} ${styles.margin_top}`}>
            <div id={styles.nav} className={styles.flex_column}>
              <Button
                id={nav === "Questions" ? styles.selected : null}
                className={styles.btn_sub}
                onClick={() => {
                  setNav("Questions");
                }}
              >
                Questions
              </Button>
              <Button
                id={nav === "Answers" ? styles.selected : null}
                className={styles.btn_sub}
                onClick={() => {
                  setNav("Answers");
                }}
              >
                Answers
              </Button>
            </div>
            <div style={{ flexGrow: 1 }}>
              <Typography sx={{ fontSize: "24px", marginBottom: "16px" }}>
                {`${
                  nav === "Questions" ? questions.length : answers.length
                } ${nav}`}
              </Typography>
              <div id={styles.list} className={`${styles.flex_column}`}>
                {list}
              </div>
              <div
                id={styles.pagination}
                className={`${styles.flex_row} ${styles.margin_top} ${styles.margin_right}`}
              >
                <CustomPagination
                  array={nav === "Questions" ? questions : answers}
                  currentPage={currentPage}
                  setCurrentPage={setCurrentPage}
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default UserProfilePage;
