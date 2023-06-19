import React from "react";
import styles from "./UserProfilePage.module.css";
import Header from "../../Components/Header/Header";
import Footer from "../../Components/Footer/Footer";
import Aside from "../../Components/Aside/Aside";
const UserProfilePage = () => {
  return (
    <div id={styles.UserProfilePage}>
      <Header />
      <div className={styles.profileBody}>
        <div id={styles.aside}>
          <Aside />
        </div>
        <div id={styles.content}>
          <div>user image</div>
          <div>
            <div>sub bar</div>
            <div>
              <div>count list</div>
              <div>list</div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default UserProfilePage;
