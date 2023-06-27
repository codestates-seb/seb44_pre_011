import { atom } from "recoil";

export const loginState = atom({
  key: "loginState",
  default: false,
});

export const userDataState = atom({
  key: "userDataState",
  default: {
    createdAt: "",
    displayName: "",
    email: "",
    memberId: "",
    modifiedAt: "",
  },
});

export const questionIdState = atom({
  key: "questionIdState",
  default: "",
});

export const memberIdState = atom({
  key: "memberIdState",
  default: "",
});