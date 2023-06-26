import axios from "axios";

export const getUsers = async () => {
  try {
    return await axios({
      method: "get",
      url: "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/members",
    });
  } catch (error) {
    console.error("Error getting users", error);
  }
};
export const getQuestions = async () => {
  try {
    return await axios({
      method: "get",
      url: "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions",
    });
  } catch (error) {
    console.error("Error getting questions", error);
  }
};
export const getUser = async (memberId) => {
  try {
    return await axios({
      method: "get",
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/members/${memberId}`,
    });
  } catch (error) {
    console.error("Error getting user", error);
  }
};
export const getList = async (tab, memberId) => {
  try {
    return await axios({
      method: "get",
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/${tab}/member/${memberId}`,
    });
  } catch (error) {
    console.error("Error getting user", error);
  }
};
export const editProfile = async (memberId, file) => {
  console.log(memberId);
  console.log(file);
  try {
    return await axios({
      method: "post",
      url: `http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/members/upload/${memberId}`,
      data: file,
    });
  } catch (error) {
    console.error("Error updating profile", error);
  }
};
