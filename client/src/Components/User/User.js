import React from "react";
import { Button, Typography } from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
const User = ({ user }) => {
  return (
    <Button
      href={`/users/${user.memberId}/${user.displayName}?tab=questions`}
      sx={{
        display: "flex",
        justifyContent: "flex-start",
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
      {user.userImage ? (
        <img
          src={user.userImage}
          alt={`user${user.displayName}`}
          style={{
            width: "96px",
            height: "96px",
            marginRight: "16px",
            borderRadius: "8px",
          }}
        />
      ) : (
        <PersonIcon
          sx={{
            width: "96px",
            height: "96px",
            marginRight: "16px",
            color: "white",
            bgcolor: "#ccc",
            borderRadius: "8px",
          }}
        />
      )}
      <Typography
        sx={{
          width: "80px",
          height: "100%",
          wordWrap: "break-word",
        }}
      >
        {user.displayName}
      </Typography>
    </Button>
  );
};

export default User;
