import style from "./Oauth.module.css";
import { Link } from "react-router-dom";

//test
const OauthComponent = (props) => {
  return (
    <Link to='http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/oauth2/authorization/google'>
      <button className={style.btnContainer_Google}>
        <img
          src={`${process.env.PUBLIC_URL}/img/oauth_google_btn.png`}
          alt="oauth_google_btn"
        ></img>
        {props.value}
      </button>
    </Link>
  );
};

export default OauthComponent;
