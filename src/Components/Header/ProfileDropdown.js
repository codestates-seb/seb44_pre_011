import React from "react";
import { NavLink } from "react-router-dom";
import Style from "./ProfileDropdown.module.css";

export default function DropMenu() {
  return (
    <>
      <div>
        <div className={Style.MenuItem}>
          <NavLink to="/mypage" className={Style.btn}>My page</NavLink>
        </div>
        <div className={Style.MenuItem}>
          <NavLink to="/editprofile" className={Style.btn}>Edit Profile</NavLink>
        </div>
        <div className={Style.MenuItem}>
          <NavLink to="/" className={Style.btn}>Logout</NavLink>
        </div>
      </div>
    </>
  );
}
