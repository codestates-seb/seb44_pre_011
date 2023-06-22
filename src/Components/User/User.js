import React from "react";
import styles from "./User.module.css"
const User = ({ user }) => {
  return (
    <div id={styles.user} className={`${styles.flex_row}`}>
      <img src={user.userImage} alt={`user${user.displayName}`} id={styles.image}/>
      <span>{user.displayName}</span>
    </div>
  );
};

export default User;
