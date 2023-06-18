import React from "react";
import { NavLink } from "react-router-dom";
import Style from "./ProfileDropdown.module.css";

// const deactiveStyle = {
//   textDecoration: "none",
//   color: "black",
// };

export default function DropMenu() {
  return (
    <>
      <div>
        <div className={Style.menuItem}>
          <NavLink to="/mypage">My page</NavLink>
        </div>
        <div>
          <NavLink to="/editprofile">Edit Profile</NavLink>
        </div>
        <div>
          <NavLink to="/">Logout</NavLink>
        </div>
      </div>
    </>
  );
}
