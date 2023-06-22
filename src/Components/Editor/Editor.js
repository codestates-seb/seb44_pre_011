import React from "react";
import { CKEditor } from "@ckeditor/ckeditor5-react";
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import './Editor.css'

const Editor = (onChange) => {
  return (
    <div className="editor-container">
        <CKEditor
          editor={ClassicEditor}
          data=""
          onChange={(event, editor) => {
          }}
        />
    </div>
  );
};

export default Editor;