import style from "./Oauth.module.css";

const OauthComponent = () =>{
    return(
        <button className={style.btnContainer_Google}>
            <img src={`${process.env.PUBLIC_URL}/img/oauth_google_btn.png`}></img>
            Log in with Google
        </button>
    );
};

export default OauthComponent;