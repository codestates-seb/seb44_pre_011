import React, { useEffect, useState } from "react";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import styles from "./Users.module.css";
import Aside from "../../Components/Aside/Aside";
import { Typography } from "@mui/material";
import User from "../../Components/User/User";
import CustomPagination from "../../Components/Pagination/CustomPagination";
import SearchIcon from "@mui/icons-material/Search";
import { makeList } from "../../Function/wrapperFunction";
// 더미 데이터
const userInfo = {
  memberId: 1,
  displayName: "FASTFOX",
  email: "dhtmdcks1325@gmail.com",
  userImage:
    "https://lh3.googleusercontent.com/a/AAcHTtelXBjmABo2mqEjXfeLRF7MXkN4kyZNZ7lEctOHag=k-s256",
};
const users = [
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
  userInfo,
];
const Users = () => {
  const [userList, setUserList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  useEffect(() => {
    setUserList(
      makeList(users, currentPage, 20).map((user, idx) => (
        <User key={`user_${idx}`} user={user} />
      ))
    );
  }, [currentPage]);
  return (
    <div className={`${styles.flex_column}`}>
      <div id={styles.field} className={`${styles.flex_column}`}>
        <Header />
        <div
          className={`${styles.flex_row} ${styles.justify_center} ${styles.flexGrow_1}`}
        >
          <div id={`${styles.aside}`}>
            <Aside />
          </div>
          <div id={styles.content} className={`${styles.flex_column}`}>
            <div className={`${styles.flex_column} ${styles.flexGrow_1}`}>
              <Typography sx={{ fontSize: "30px" }}>Users</Typography>
              <span
                id={styles.SearchBarContainer}
                className={`${styles.flex_row} ${styles.alignItems_center} ${styles.margin_top_16}`}
              >
                <SearchIcon
                  id={styles.searchIcon}
                  sx={{ width: "30px", height: "100%" }}
                />
                <input
                  className={styles.searchBar}
                  placeholder="Filter by user"
                  type="text"
                />
              </span>
              <div id={styles.userList} className={`${styles.flex_row}`}>
                {userList}
              </div>
            </div>
            <div id={styles.pagination} className={`${styles.flex_row}`}>
              <CustomPagination
                array={users}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                pageSize={20}
              />
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default Users;
