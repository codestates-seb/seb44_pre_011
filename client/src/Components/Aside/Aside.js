import React from "react";
import { Typography } from "@mui/material";
import QuestionMarkIcon from "@mui/icons-material/QuestionMark";
import LocalOfferIcon from "@mui/icons-material/LocalOffer";
import PeopleAltIcon from "@mui/icons-material/PeopleAlt";
import SettingsIcon from "@mui/icons-material/Settings";
import { NavLink } from "react-router-dom";
import "./aside.css";

const Aside = () => {
  return (
    <div id="Btns_aside">
      <NavLink
        to="/questions"
        className="asideButton"
        activeclassname="asideButton active"
      >
        <QuestionMarkIcon className="icon" />
        <Typography>Questions</Typography>
      </NavLink>
      <NavLink
        to="/tags"
        className="asideButton "
        activeclassname="asideButton active"
      >
        <LocalOfferIcon className="icon" />
        <Typography>Tags</Typography>
      </NavLink>
      <NavLink
        to="/users"
        className="asideButton "
        activeclassname="asideButton active"
      >
        <PeopleAltIcon className="icon" />
        <Typography>Users</Typography>
      </NavLink>
      <NavLink
        to="about"
        className="asideButton "
        activeclassname="asideButton active"
      >
        <SettingsIcon className="icon" />
        <Typography>About</Typography>
      </NavLink>
    </div>
  );
};

export default Aside;
