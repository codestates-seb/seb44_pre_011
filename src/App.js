import { Route, Routes } from "react-router-dom";
import MainPage from "./Pages/Main/MainPage";
import ReadQuestionPage from "./Pages/ReadQuestion/ReadQuestionPage";
import LoginPage from "./Pages/Login/LoginPage";
import SignupPage from "./Pages/Signup/SignupPage";
import UserProfilePage from "./Pages/UserProfile/UserProfilePage";
import CreateQuestionPage from "./Pages/CreateQuestion/CreateQuestionPage";
import "./App.module.css";

function App() {
  const memberId = 1;
  const displayName = "FASTFOX";
  
  return (
    <Routes>
      <Route element={<MainPage />} path="/" />
      <Route element={<ReadQuestionPage />} path="/questions/read" />
      <Route element={<LoginPage />} path="/login" />
      <Route element={<SignupPage />} path="/signup" />
      <Route
        element={<UserProfilePage memberId={memberId} />}
        path={`/users/${memberId}/${displayName}`}
      />
      <Route element={<CreateQuestionPage />} />
    </Routes>
  );
}

export default App;
