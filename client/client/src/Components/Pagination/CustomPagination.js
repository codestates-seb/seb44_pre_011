import React from "react";
import { Pagination } from "@mui/material";

const CustomPagination = ({ array, currentPage, setCurrentPage, pageSize }) => {
  return (
    <Pagination
      count={Math.ceil(array.length / pageSize)}
      page={currentPage}
      variant="outlined"
      shape="rounded"
      sx={{
        "& .MuiPaginationItem-root": {
          color: "black",
          "&.Mui-selected": {
            color: "white",
            bgcolor: "#f48225",
          },
          "&:hover": {
            bgcolor: "#f48225",
          },
        },
      }}
      onChange={(_, value) => {
        setCurrentPage(value);
        window.scrollTo(0, 0);
      }}
    />
  );
};

export default CustomPagination;
