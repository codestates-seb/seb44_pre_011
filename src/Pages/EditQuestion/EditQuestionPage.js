import React from 'react'
import Header from '../../Components/Header/Header'
import style from './EditQuestionPage.module.css'
import Button from "@mui/material/Button";
import Editor from '../../Components/Editor/Editor';

const EditQuestionPage = () => {
  
  return (
    <>
      <Header/>
      <div className={style.Allcontainer}>
        <div className={style.contents} id={style.first}><h2>Edit your question</h2></div>
        <div className={style.contents} id={style.title}>
          <div className={style.title_div1}>Title</div>
          <div className={style.title_div2}>Be specific and imagine you’re asking a question to another person.</div>
          <div id={style.title_div3}>
            <input type='text' className={style.title_div3_input} placeholder='e.g. Is there an R function for finding the index of an element in a vector? '/>
          </div>
        </div>
        <div className={style.contents} id={style.problem}>
          <div className={style.title_div1}>What are the details of your problem?</div>
          <div className={style.title_div2}>Introduce the problem and expand on what you put in the title. Minimum 20 characters.</div>
          <div id={style.problem_div1}>
            <Editor/>
          </div>
        </div>
        <div className={style.contents} id={style.tags}>
          <div className={style.title_div1}>tags</div>
          <div className={style.title_div2}>Add up to 5 tags to describe what your question is about. Start typing to see suggestions.</div>
          <div id={style.tags_div}>
            <input type='text' className={style.title_div3_input} placeholder='e.g. (angular regex django) '/>
          </div>
        </div>
        <div className={style.contents}>
          <Button
                  variant="contained"
                  sx={{
                    fontSize: 12,
                    height: "40px",
                    marginLeft: "25px",
                    marginBottom:"20px"
                  }}
                >Submit
            </Button>
        </div>
      </div>
    </>
  )
}

export default EditQuestionPage