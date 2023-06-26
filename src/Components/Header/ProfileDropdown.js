import React from "react";
import { NavLink } from "react-router-dom";
import Style from "./ProfileDropdown.module.css";
import { useSetRecoilState } from "recoil";
import { loginState } from "../../store/auth";

export default function DropMenu() {
  const setLoginState = useSetRecoilState(loginState);

  const handleLogout = () => {
    sessionStorage.removeItem("token"); // 토큰 삭제
    setLoginState(false);
  };

  return (
    <>
      <div>
        <div className={Style.MenuItem}>
          <NavLink to="/mypage" className={Style.btn}>
            My page
          </NavLink>
        </div>
        <div className={Style.MenuItem}>
          <NavLink to="/editprofile" className={Style.btn}>
            Edit Profile
          </NavLink>
        </div>
        <div className={Style.MenuItem} onClick={handleLogout}>
          <NavLink to="/questions" className={Style.btn}>
            Logout
          </NavLink>
        </div>
      </div>
    </>
  );
}
