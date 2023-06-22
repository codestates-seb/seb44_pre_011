import React from 'react'
import Header from '../../Components/Header/Header'
import style from './CreateQusetionPage.module.css'
import Button from "@mui/material/Button";
import Editor from '../../Components/Editor/Editor';

const CreateQuestionPage = () => {
  return (
    <>
      <Header/>
      <div className={style.Allcontainer}>
        <div className={style.contents} id={style.first}><h2>Ask a public question</h2></div>
        <div className={style.contents} id={style.tip}>
          <div id={style.tip_div1}>Writing a good question</div>
          <div id={style.tip_div2}>You’re ready to ask a programming-related question and this form will help guide you through the process.
            Looking to ask a non-programming question? See the topics here to find a relevant site.</div>
          <div id={style.tip_div3}>Steps</div>
          <div id={style.tip_div4}>
            <ul id={style.tip_ul}>
              <li>Summarize your problem in a one-line title.</li>
              <li>Describe your problem in more detail.</li>
              <li>Describe what you tried and what you expected to happen.</li>
              <li>Add “tags” which help surface your question to members of the community.</li>
              <li>Review your question and post it to the site.</li>
            </ul>
          </div>
        </div>
        <div className={style.contents} id={style.title}>
          <div className={style.title_div1}>Title</div>
          <div className={style.title_div2}>Be specific and imagine you’re asking a question to another person.</div>
          <div id={style.title_div3}>
            <input type='text' id={style.title_div3_input} placeholder='e.g. Is there an R function for finding the index of an element in a vector? '/>
          </div>
          <div>
            <Button
              variant="contained"
              sx={{
                fontSize: 12,
                height: "40px",
                marginTop: "10px",
                marginLeft: "25px",
                marginBottom:"25px"
              }}
            >next
            </Button>
          </div>
        </div>
        <div className={style.contents} id={style.problem}>
          <div className={style.title_div1}>What are the details of your problem?</div>
          <div className={style.title_div2}>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</div>
          <div>
            <Editor/>
          </div>
        </div>
        <div className={style.contents}>5</div>
        <div className={style.contents}>6</div>
      </div>
    </>
  )
}

export default CreateQuestionPage