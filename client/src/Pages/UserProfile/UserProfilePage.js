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
import PersonIcon from "@mui/icons-material/Person";
import { makeList } from "../../Function/wrapperFunction";
import { useLocation, useParams } from "react-router-dom";
import { getList, getQuestions, getUser } from "../../Function/api";
const UserProfilePage = () => {
  const id = sessionStorage.getItem("id");
  const { memberId, displayName } = useParams();
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const tab = searchParams.get("tab");
  const [user, setUser] = useState({});
  const [items, setItems] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const handleList = makeList(items, currentPage, 15).map((item, idx) => (
    <Questions
      key={`${tab}_${idx}`}
      questionId={item.questionId}
      title={item.title}
      content={item.content}
      createdAt={item.createdAt}
      displayName={item.displayName}
    />
  ));
  useEffect(() => {
    (async () => {
      try {
        await getUser(memberId).then((res) => {
          setUser(res.data.data);
        });
        if (tab === "questions") {
          await getList(tab, memberId).then((res) => {
            setItems(res.data);
          });
        } else {
          const arr_1 = [];
          await getList(tab, memberId).then((res) => {
            res.data.forEach((answer) => {
              if (!arr_1.includes(answer.questionId)) {
                arr_1.push(answer.questionId);
              }
            });
          });
          await getQuestions().then((res) => {
            setItems(
              res.data.filter((question) => arr_1.includes(question.questionId))
            );
          });
        }
      } catch (error) {
        console.error("Error getting user Information", error);
      }
    })();
  }, [tab]);
  return (
    <div className={styles.flex_column}>
      <div id={styles.field} className={`${styles.flex_column}`}>
        <Header />
        <div
          className={`${styles.flex_row} ${styles.justify_center} ${styles.flexGrow_1}`}
        >
          <div id={styles.aside}>
            <Aside />
          </div>
          <div id={styles.content} className={`${styles.flex_column}`}>
            <div className={`${styles.flex_column} ${styles.flexGrow_1}`}>
              <div className={`${styles.flex_row}`}>
                {user.userImage ? (
                  <img
                    alt="user"
                    src={user.userImage}
                    id={styles.user_image}
                    className={styles.margin_right}
                  />
                ) : (
                  <PersonIcon
                    sx={{ width: "128px", height: "128px", color: "#ccc" }}
                  />
                )}
                <div
                  id={styles.userInfo}
                  className={`${styles.flex_column} ${styles.justify_center}`}
                >
                  <span id={styles.displayName}>{displayName}</span>
                  <span
                    id={styles.user_info_sub}
                    className={`${styles.flex_row} ${styles.alignItems_center} ${styles.margin_top}`}
                  >
                    <EmailIcon
                      sx={{ color: "#9298a1", fontSize: "18px" }}
                      className={styles.margin_right}
                    />
                    {user.email}
                  </span>
                </div>
                {memberId === id ? (
                  <div className={`${styles.flex_row}`}>
                    <Button
                      sx={{
                        textTransform: "none",
                        height: "40px",
                        border: "1px solid #6a737c",
                        color: "#6a737c",
                      }}
                      href={`/users/edit/${id}`}
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
                    id={tab === "questions" ? styles.selected : null}
                    className={styles.btn_sub}
                    href={`/users/${memberId}/${displayName}?tab=questions`}
                  >
                    Questions
                  </Button>
                  <Button
                    id={tab === "answers" ? styles.selected : null}
                    className={styles.btn_sub}
                    href={`/users/${memberId}/${displayName}?tab=answers`}
                  >
                    Answers
                  </Button>
                </div>
                <div className={`${styles.flex_column} ${styles.flexGrow_1}`}>
                  <Typography sx={{ fontSize: "24px", marginBottom: "16px" }}>
                    {`${items.length} ${tab}`}
                  </Typography>
                  <div id={styles.list} className={`${styles.flex_column} `}>
                    {items.length ? (
                      handleList
                    ) : (
                      <Typography
                        sx={{
                          display: "flex",
                          height: "50vh",
                          alignItems: "center",
                          justifyContent: "center",
                          color: "gray",
                        }}
                      >
                        List is empty.
                      </Typography>
                    )}
                  </div>
                </div>
              </div>
            </div>
            <div
              id={styles.pagination}
              className={`${styles.flex_row} ${styles.margin_top} ${styles.margin_right}`}
            >
              <CustomPagination
                array={items}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                pageSize={15}
              />
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default UserProfilePage;
