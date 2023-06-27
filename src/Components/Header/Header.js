<<<<<<< HEAD
import React from 'react'

const Header = () => {
=======
import Style from "./Header.module.css";
import { Link } from "react-router-dom";
import { useState, useEffect } from "react";
import SearchIcon from "@mui/icons-material/Search";
import Button from "@mui/material/Button";
import MenuIcon from "@mui/icons-material/Menu";
import Aside from "../Aside/Aside";
import ProfileDropdown from "./ProfileDropdown";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { loginState, userDataState } from "../../store/auth";
import axios from "axios";

const Header = () => {
  const [menuView, setMenuView] = useState(false);

  const toggleDropdown = () => {
    setMenuView(!menuView);
  };

  const isLogin = useRecoilValue(loginState);
  const setLoginState = useSetRecoilState(loginState);
  const setUserDataState = useSetRecoilState(userDataState);
  useEffect(() => {
    const token = sessionStorage.getItem("token");
    const memberId = sessionStorage.getItem("id");

    if (token) {
      setLoginState(true);

      axios
        .get(
          `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`,
          { headers: { authorization: `Bearer ${token}` } }
        )
        .then((res) => {
          if (res.status === 200) {
            setUserDataState({
              createdAt: res.data.data.createdAt,
              displayName: res.data.data.displayName,
              email: res.data.data.email,
              memberId: res.data.data.memberId,
              modifiedAt: res.data.data.modifiedAt,
            });
          }
        })
        .catch((err) => {
          console.log(err.response.data);
        });
    }
  }, []);

>>>>>>> dev
  return (
    <div>Header</div>
  )
}

<<<<<<< HEAD
export default Header
=======
      <Link to="/questions">
        <img
          className={Style.logo}
          src={`${process.env.PUBLIC_URL}/img/logo-stackoverflow.png`}
          alt="logo"
        ></img>
      </Link>
      <span className={Style.SearchBarContainer}>
        <SearchIcon
          className={Style.searchIcon}
          sx={{ width: "30px", height: "100%" }}
        />
        <input
          className={Style.searchBar}
          placeholder="Search..."
          type="text"
        />
      </span>

      {isLogin ? <UserInfo /> : <LinkButton />}
    </header>
  );
};

export default Header;

const UserInfo = () => {
  const [menuView, setMenuView] = useState(false); //프로필 클릭시 드롭다운 생성
  const isUserData = useRecoilValue(userDataState);
  const Dropdown = () => {
    setMenuView(!menuView);
  };

  return (
    <div className={Style.profileContainer}>
      <img
        className={Style.defaultPicture}
        src={`${process.env.PUBLIC_URL}/img/test_img.jpg`}
        alt="default_picture"
        onClick={Dropdown}
      ></img>
      <div className={Style.ProfileDropdowncontainer}>
        {menuView && <ProfileDropdown className={Style.MenuItem} />}
      </div>
      <div>{isUserData.displayName}</div>
    </div>
  );
};

const LinkButton = () => {
  return (
    <span className={Style.buttonContainer}>
      <Link to="/login">
        <Button
          className={Style.button}
          variant="contained"
          sx={{
            fontSize: 14,
            width: "80px",
            height: "40px",
            margin: "10px",
            backgroundColor: "#e3ecf3",
            color: "#1976D2",
            ":hover": {
              color: "#e3ecf3",
            },
          }}
        >
          {" "}
          Login
        </Button>
      </Link>
      <Link to="/signup">
        <Button
          className={Style.button}
          variant="contained"
          sx={{ fontSize: 14, width: "93px", height: "40px" }}
        >
          Sign Up
        </Button>
      </Link>
    </span>
  );
};
>>>>>>> dev
