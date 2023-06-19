import "./App.module.css";
import { Route, Routes } from "react-router-dom";
import MainPage from "./Pages/Main/MainPage";
import LoginPage from "./Pages/Login/LoginPage";
import SignupPage from "./Pages/Signup/SignupPage";
import UserProfilePage from "./Pages/UserProfile/UserProfilePage";
import CreateQuestionPage from "./Pages/CreateQuestion/CreateQuestionPage";
import "./App.module.css";

function App() {
  
  return (
    <Routes>
      <Route element={<MainPage />} path="/" />
      <Route element={<LoginPage />} path="/login" />
      <Route element={<SignupPage />} path="/signup" />
      <Route element={<UserProfilePage />} path="/users" />
      <Route element={<CreateQuestionPage />} />
    </Routes>
  );
}

export default App;
