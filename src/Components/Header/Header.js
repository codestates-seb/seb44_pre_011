import Style from "./Header.module.css";
import { Link } from "react-router-dom";
import { useState } from "react";
import SearchIcon from "@mui/icons-material/Search";
import Button from "@mui/material/Button";
import MenuIcon from "@mui/icons-material/Menu";
import Aside from "../Aside/Aside";

const Header = () => {
  const [view, setView] = useState(false);

  const toggleDropdown = () => {
    setView(!view);
  };

  return (
    <header className={Style.headerContainer}>
      <div className={Style.Container}>
        {(window.location.pathname === "/login" ||
          window.location.pathname === "/signup") && (
          <MenuIcon onClick={toggleDropdown} />
        )}
        <div className={Style.dropdownContainer}>
          {view && <Aside className={Style.dropdown} />}
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
        <SearchIcon className={Style.searchIcon} />
        <input className={Style.searchBar} type="text" />
      </span>
      <span className={Style.buttonBox}>
        <Link to="/login">
          <Button className={Style.button} variant="contained">
            Log IN
          </Button>
        </Link>

        <Link>
          <Button className={Style.button} variant="contained">
            Sign Up
          </Button>
        </Link>
      </span>
    </header>
  );
};

export default Header;
