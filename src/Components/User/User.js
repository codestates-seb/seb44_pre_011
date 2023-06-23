import React from "react";
import styles from "./User.module.css";
import { Button, Typography } from "@mui/material";
const User = ({ user }) => {
  return (
    <Button
      href={`/users/${user.memberId}/${user.displayName}`}
      sx={{
        display: "flex",
        padding: 0,
        textTransform: "none",
        width: "22%",
        height: "96px",
        margin: "0px 3% 32px 0px",
        color: "black",
        "&:hover": {
          bgcolor: "white",
        },
      }}
    >
      <img
        src={user.userImage}
        alt={`user${user.displayName}`}
        id={styles.image}
      />
      <Typography sx={{ height: 1 }}>{user.displayName}</Typography>
    </Button>
  );
};

export default User;
