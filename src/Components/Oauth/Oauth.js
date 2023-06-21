import style from "./Oauth.module.css";

//test
const OauthComponent = (props) => {
  return (
    <button className={style.btnContainer_Google}>
      <img src={`${process.env.PUBLIC_URL}/img/oauth_google_btn.png`}></img>
      {props.value}
    </button>
  );
};

export default OauthComponent;
