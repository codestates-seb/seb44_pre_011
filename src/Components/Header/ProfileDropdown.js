import React from "react";
import { NavLink } from "react-router-dom";
import Style from "./ProfileDropdown.module.css";
import { useSetRecoilState } from "recoil";
import { loginState } from "../../store/auth";
import { useRecoilValue} from "recoil";
import {userDataState } from "../../store/auth";

export default function DropMenu() {
  const setLoginState = useSetRecoilState(loginState);

  const handleLogout = () => {
    sessionStorage.removeItem("token"); // 토큰 삭제
    sessionStorage.removeItem("id"); //id 삭ㅔ
    setLoginState(false);
  };

  const isUserData = useRecoilValue(userDataState);

  return (
    <>
      <div>
        <div className={Style.MenuItem}>
          <NavLink to={`/users/${isUserData.memberId}/${isUserData.displayName}?tab=questions`} className={Style.btn}>
            My page
          </NavLink>
        </div>
        <div className={Style.MenuItem}>
          <NavLink to={`/users/edit/${isUserData.memberId}`} className={Style.btn}>
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
