import React, { useEffect, useState } from "react";
import styles from "./EditProfile.module.css";
import Header from "../../Components/Header/Header";
import Aside from "../../Components/Aside/Aside";
import Footer from "../../Components/Footer/Footer";
import PersonIcon from "@mui/icons-material/Person";
import EmailIcon from "@mui/icons-material/Email";
import { Button, Divider, TextField, Typography } from "@mui/material";
import { editProfile, getUser } from "../../Function/api";
import { useNavigate } from "react-router-dom";
const EditProfile = () => {
  const navigate = useNavigate();
  const id = sessionStorage.getItem("id");
  const [user, setUser] = useState({});
  const [updateInfo, setUpdateInfo] = useState({
    displayName: "",
    email: "",
  });
  const [imageData, setImageData] = useState({
    image: user?.image,
    uploadImage: "",
  });
  const clickSave = async () => {
    try {
      await editProfile(user.memberId, updateInfo).then((res) => {
        navigate(`/users/${id}/${updateInfo.displayName}/?tab=questions`);
      });
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    (async () => {
      try {
        await getUser(id).then((res) => {
          setUser(res.data.data);
          setUpdateInfo({
            displayName: res.data.data.displayName,
            email: res.data.data.email,
          });
        });
      } catch (error) {
        console.error("Error getting user", error);
      }
    })();
  }, []);
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
                  <span id={styles.displayName}>{user.displayName}</span>
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
              </div>
              <div className={`${styles.flex_column} ${styles.margin_top}`}>
                <Typography sx={{ fontSize: "31px" }}>
                  Edit your profile
                </Typography>
                <Divider sx={{ margin: "16px 0px" }} />
                <div className={`${styles.flex_column}`}>
                  <Typography sx={{ fontWeight: "bold" }}>
                    Profile image
                  </Typography>
                  {imageData.image ? (
                    <img
                      src={imageData.image}
                      alt="user"
                      id={styles.user_image}
                    />
                  ) : (
                    <PersonIcon
                      sx={{ width: "164px", height: "164px", color: "#ccc" }}
                    />
                  )}
                  <Button
                    variant="contained"
                    component="label"
                    sx={{
                      width: "164px",
                      textTransform: "none",
                      color: "white",
                      bgcolor: "#525960",
                    }}
                  >
                    Change picture
                    <input
                      hidden
                      type="file"
                      onChange={(event) => {
                        setImageData({
                          image: URL.createObjectURL(event.target.files[0]),
                          uploadImage: event.target.files[0],
                        });
                      }}
                    />
                  </Button>
                </div>
                <div>
                  <Typography sx={{ fontWeight: "bold", marginTop: "16px" }}>
                    Display name
                  </Typography>
                  <TextField
                    sx={{ width: "300px", marginTop: "8px" }}
                    value={updateInfo.displayName}
                    onChange={(ev) => {
                      setUpdateInfo({
                        ...updateInfo,
                        displayName: ev.target.value,
                      });
                    }}
                  />
                </div>
                <div>
                  <Typography sx={{ fontWeight: "bold", marginTop: "16px" }}>
                    Email
                  </Typography>
                  <TextField
                    sx={{ width: "300px", marginTop: "8px" }}
                    value={updateInfo.email}
                    onChange={(ev) => {
                      setUpdateInfo({
                        ...updateInfo,
                        email: ev.target.value,
                      });
                    }}
                  />
                </div>
                <div className={`${styles.margin_top}`}>
                  <Button
                    sx={{
                      textTransform: "none",
                      height: "40px",
                      bgcolor: "#0a95ff",
                      color: "white",
                      "&:hover": {
                        bgcolor: "#0a95ff",
                      },
                    }}
                    onClick={() => {
                      clickSave();
                    }}
                  >
                    Save profile
                  </Button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
};

export default EditProfile;
