import Style from "./Header.module.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import Button from "@mui/material/Button";
import MenuIcon from "@mui/icons-material/Menu";
import Aside from "../Aside/Aside";

const Header = () => {
  const [menuView, setMenuView] = useState(false);

  const [isLogin] = useState(false);

  const toggleDropdown = () => {
    setMenuView(!menuView);
  };

  return (
    <header className={Style.headerContainer}>
      <div className={Style.Container}>
        {(window.location.pathname === "/login" ||
          window.location.pathname === "/signup") && (
          <MenuIcon
            onClick={toggleDropdown}
            sx={{ display: "flex", height: "100%", width: "25px" }}
          />
        )}
        <div className={Style.dropdownContainer}>
          {menuView && <Aside className={Style.dropdown} />}
        </div>
      </div>

      <Link to="/">
        <img
          className={Style.logo}
          src={`${process.env.PUBLIC_URL}/img/logo-stackoverflow.png`}
          alt="logo"
        ></img>
      </Link>
      <span className={Style.SearchBarContainer}>
        <SearchIcon
          className={Style.searchIcon}
          sx={{ width: "25px", height: "100%" }}
        />
        <input className={Style.searchBar} type="text" />
      </span>

      {isLogin ? <UserInfo /> : <LinkButton />}
    </header>
  );
};

export default Header;

const UserInfo = () => {
  return (
    <div className={Style.profileContainer}>
      <img
        className={Style.defaultPicture}
        src={`${process.env.PUBLIC_URL}/img/test_img.jpg`}
        alt="default_picture"
      ></img>
      <div>display name</div>
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
            fontSize: "14px",
            width: "93px",
            height: "30px",
          }}
        >
          Log IN
        </Button>
      </Link>
      <Link to="/signup">
        <Button
          className={Style.button}
          variant="contained"
          sx={{ fontSize: 14, width: "93px", height: "30px" }}
        >
          Sign Up
        </Button>
      </Link>
    </span>
  );
};
