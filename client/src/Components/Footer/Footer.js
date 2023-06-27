import React from "react";
import styles from "./Footer.module.css";
import { Typography } from "@mui/material";

const Footer = () => {
  const stackoverflow = [
    { name: "Questions", href: "https://stackoverflow.com/questions" },
    { name: "Help", href: "https://stackoverflow.com/help" },
  ];
  const products = [
    { name: "Teams", href: "https://stackoverflow.co/teams/" },
    { name: "Advertising", href: "https://stackoverflow.co/advertising/" },
    { name: "collectives", href: "https://stackoverflow.co/collectives/" },
    { name: "Talent", href: "https://stackoverflow.co/talent/" },
  ];
  const company = [
    { name: "About", href: "https://stackoverflow.co/" },
    { name: "Press", href: "https://stackoverflow.co/company/press" },
    { name: "Work Here", href: "https://stackoverflow.co/company/work-here" },
    { name: "Legal", href: "https://stackoverflow.com/legal/terms-of-service" },
    {
      name: "Privacy Policy",
      href: "https://stackoverflow.com/legal/privacy-policy",
    },
    {
      name: "Terms of Service",
      href: "https://stackoverflow.com/legal/terms-of-service",
    },
    { name: "Contact Us", href: "https://stackoverflow.co/company/contact" },
    { name: "Cookie Settings", href: "" },
    {
      name: "Cookie Policy",
      href: "https://stackoverflow.com/legal/cookie-policy",
    },
  ];
  const network = [
    { name: "Technology", href: "https://stackexchange.com/sites#technology" },
    {
      name: "Culture & recreation",
      href: "https://stackexchange.com/sites#culturerecreation",
    },
    { name: "Life & arts", href: "https://stackexchange.com/sites#lifearts" },
    { name: "Science", href: "https://stackexchange.com/sites#science" },
    {
      name: "Professional",
      href: "https://stackexchange.com/sites#professional",
    },
    { name: "Business", href: "https://stackexchange.com/sites#business" },
    { name: "", href: "" },
    { name: "API", href: "https://api.stackexchange.com/" },
    { name: "Data", href: "https://data.stackexchange.com/" },
  ];
  const footerBtns = (arr) => {
    return arr.map((a, idx) => (
      <li key={`stackoverflowBtns${idx}`}>
        <a href={a.href} className={styles.footerBtn}>
          {a.name}
        </a>
      </li>
    ));
  };
  return (
    <div id={styles.footerContainer}>
      <img
        src={`${process.env.PUBLIC_URL}/img/Stack_Overflow-Icon-Logo.wine (1).png`}
        alt="stackoverflow logo"
        className={styles.logo}
      />
      <div className={styles.footerBtns}>
        <Typography className={styles.footerMSGS}>STACK OVERFLOW</Typography>
        <ul>{footerBtns(stackoverflow)}</ul>
      </div>
      <div className={styles.footerBtns}>
        <Typography className={styles.footerMSGS}>RODUCTS</Typography>
        <ul>{footerBtns(products)}</ul>
      </div>
      <div className={styles.footerBtns}>
        <Typography className={styles.footerMSGS}>COMPANY</Typography>
        <ul>{footerBtns(company)}</ul>
      </div>
      <div className={styles.footerBtns}>
        <Typography className={styles.footerMSGS}>
          STACK EXCHANGE NETWORK
        </Typography>
        <ul>{footerBtns(network)}</ul>
      </div>
    </div>
  );
};

export default Footer;
