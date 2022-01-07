import React, { useState, useEffect } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableContainer from '@mui/material/TableContainer';
import TwoRowData from './TwoRowData';
import axios from "axios";

const baseURL = "http://localhost:8088/members/title-info";

export default function TitleInfo() {
  const [item, setItem] = useState(null);
  
  useEffect(() => {
    axios.get(baseURL).then((response) => {
      setItem(response.data);
    });
  }, []);

  if (!item) return null;
    
  return (
    <TableContainer >
      <Table sx={{ minWidth: 250, maxWidth: 500 }} aria-label="simple table">
        <TableBody>
          <TwoRowData label="congressNum" value={item['congressNum']} />
          <TwoRowData label="congressText" value={item['congressText']} />
          <TwoRowData label="congressNum" value={item['congressNum']} />
          <TwoRowData label="majority" value={item['majority']} />
          <TwoRowData label="minority" value={item['minority']} />
          <TwoRowData label="clerk" value={item['clerk']} />
          <TwoRowData label="weburl" value={item['weburl']} />
        </TableBody>
      </Table>
    </TableContainer>
  );
}
