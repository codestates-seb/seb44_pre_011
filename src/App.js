import { Route, Routes } from "react-router-dom";
import MainPage from "./Pages/Main/MainPage";
import ReadQuestionPage from "./Pages/ReadQuestion/ReadQuestionPage";
import LoginPage from "./Pages/Login/LoginPage";
import SignupPage from "./Pages/Signup/SignupPage";
import UserProfilePage from "./Pages/UserProfile/UserProfilePage";
import CreateQuestionPage from "./Pages/CreateQuestion/CreateQuestionPage";
import "./App.module.css";
import Users from "./Pages/Users/Users";
import EditQuestionPage from "./Pages/EditQuestion/EditQuestionPage";
import EditProfile from "./Pages/EditProfile/EditProfile";

function App() {
  return (
    <Routes>
      <Route element={<MainPage />} path="/questions" />
      <Route element={<LoginPage />} path="/login" />
      <Route element={<SignupPage />} path="/signup" />
      <Route element={<ReadQuestionPage />} path="/questions/read" />
      <Route element={<Users />} path="/users" />
      <Route
        element={<UserProfilePage />}
        path={"/users/:memberId/:displayName"}
      />
      <Route element={<EditProfile />} path={"/users/edit/:memberId"} />
      <Route element={<CreateQuestionPage />} path="/questions/ask" />
      <Route element={<EditQuestionPage />} path="/questions/edit" />
    </Routes>
  );
}

export default App;
