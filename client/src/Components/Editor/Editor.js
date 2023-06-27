import React, { useState } from "react";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import "./Editor.css";

const Editor = ({ text, setText }) => {
  const [bodyMsg, setBodyMsg] = useState("");
  // const [editorData, setEditorData] = useState(""); // 입력된 값을 저장할 상태

  const handleBody = (e, editor) => {
    const data = editor.getData(); // 입력된 값 가져오기
    const edit_data = data.replace(/(<([^>]+)>)/gi, "");
    console.log(edit_data);

    setText(edit_data); // 상태 업데이트
    if (edit_data === "") {
      setBodyMsg("❗️본문을 입력해주세요.");
    } else if (edit_data.length < 20) {
      setBodyMsg("❗️본문은 20자 이상이어야 합니다.");
    } else {
      setBodyMsg("");
    }
  };

  return (
    <div className="editor-container">
      <CKEditor editor={ClassicEditor} data={text} onBlur={handleBody} />
      <p className="err-msg">{bodyMsg}</p>
    </div>
  );
};

export default Editor;
