import Style from "./Header.module.css";
// import SearchIcon from "@mui/icons-material/Search";
import Button from "@mui/material/Button";

const Header = () => {
  return (
    <header className={Style.headerContainer}>
      <img
        className={Style.logo}
        src={`${process.env.PUBLIC_URL}/img/logo-stackoverflow.png`}
        alt="logo"
      ></img>
      <span className={Style.SearchBarContainer}>
        {/* <SearchIcon className={Style.searchIcon} /> */}
        <input className={Style.searchBar} type="text" />
      </span>
      <Button variant="contained">Log IN</Button>
      <Button variant="contained">Sign Up</Button>
    </header>
  );
};

export default Header;
