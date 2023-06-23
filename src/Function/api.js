import axios from "axios";

export const getMethod = async (method, url) => {
  return await axios({
    method: method,
    url: url,
  });
};
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
