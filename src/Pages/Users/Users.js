import React, { useEffect, useState } from "react";
import styles from "./Users.module.css";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import Aside from "../../Components/Aside/Aside";
import User from "../../Components/User/User";
import CustomPagination from "../../Components/Pagination/CustomPagination";
import { makeList } from "../../Function/wrapperFunction";
import { Typography } from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import { getUsers } from "../../Function/api";
const Users = () => {
  const [userList, setUserList] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const users = makeList(userList, currentPage, 20).map((user, idx) => (
    <User key={`user_${idx}`} user={user} />
  ));
  useEffect(() => {
    (async () => {
      try {
        await getUsers().then((res) => {
          console.log(res.data);
          setUserList(res.data);
        });
      } catch (error) {
        console.error(error);
      }
    })();
  }, []);
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
                {users}
              </div>
            </div>
            <div id={styles.pagination} className={`${styles.flex_row}`}>
              <CustomPagination
                array={userList}
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
