import { useState } from "react";
import Header from "../../Components/Header/Header";
import style from "./CreateQusetionPage.module.css";
import Button from "@mui/material/Button";
import Editor from "../../Components/Editor/Editor";
import axios from "axios";

const CreateQuestionPage = () => {
  const [title, setTitle] = useState("");
  const [titleMsg, setTitleMsg] = useState("");
  const [text, setText] = useState("");
  const [tag, setTag] = useState("");

  const Title = (e) => {
    let titleValue = e.target.value;

    if (titleValue.length < 15) {
      setTitleMsg("❗️제목은 15자 이상이어야 합니다.");
    } else {
      setTitle(titleValue);
      setTitleMsg("");
    }
  };

  const Tag = (e) => {
    let tagValue = e.target.value;

    if (tagValue !== "") {
      setTag(tagValue);
    }
  };

  const Submit = () => {
    axios({
      // url: "http://ec2-3-34-211-22.ap-northeast-2.compute.amazonaws.com:8080/questions/ask",
      method: "post",
      data: {
        title: title,
        content: text,
        tag: tag,
      },
    });
  };

  return (
    <>
      <Header />
      <div className={style.Allcontainer}>
        <div className={style.contents} id={style.first}>
          <h2>Ask a public question</h2>
        </div>
        <div className={style.contents} id={style.tip}>
          <div id={style.tip_div1}>Writing a good question</div>
          <div id={style.tip_div2}>
            You’re ready to ask a programming-related question and this form
            will help guide you through the process. Looking to ask a
            non-programming question? See the topics here to find a relevant
            site.
          </div>
          <div id={style.tip_div3}>Steps</div>
          <div id={style.tip_div4}>
            <ul id={style.tip_ul}>
              <li>Summarize your problem in a one-line title.</li>
              <li>Describe your problem in more detail.</li>
              <li>Describe what you tried and what you expected to happen.</li>
              <li>
                Add “tags” which help surface your question to members of the
                community.
              </li>
              <li>Review your question and post it to the site.</li>
            </ul>
          </div>
        </div>
        <div className={style.contents} id={style.title}>
          <div className={style.title_div1}>Title</div>

          <div className={style.title_div2}>
            Be specific and imagine you’re asking a question to another person.
          </div>
          <div id={style.title_div3}>
            <input
              type="text"
              onBlur={Title}
              className={style.title_div3_input}
              placeholder="e.g. Is there an R function for finding the index of an element in a vector? "
            />
            <p className={style.errMsg}>{titleMsg}</p>
          </div>
        </div>
        <div className={style.contents} id={style.problem}>
          <div className={style.title_div1}>
            What are the details of your problem?
          </div>
          <div className={style.title_div2}>
            Introduce the problem and expand on what you put in the title.
            Minimum 20 characters.
          </div>
          <div id={style.problem_div1}>
            <Editor text={text} setText={setText} />
          </div>
        </div>
        <div className={style.contents} id={style.tags}>
          <div className={style.title_div1}>tags</div>
          <div className={style.title_div2}>
            Add up to 5 tags to describe what your question is about. Start
            typing to see suggestions.
          </div>
          <div id={style.tags_div}>
            <input
              type="text"
              onBlur={Tag}
              className={style.title_div3_input}
              placeholder="e.g. (angular regex django) "
            />
          </div>
        </div>
        <div className={style.contents}>
          <Button
            variant="contained"
            sx={{
              fontSize: 12,
              height: "40px",
              marginLeft: "25px",
              marginBottom: "20px",
            }}
            onClick={Submit}
            disabled={""}
          >
            Submit
          </Button>
        </div>
      </div>
    </>
  );
};

export default CreateQuestionPage;
