
import TableCell from '@mui/material/TableCell';
import TableRow from '@mui/material/TableRow';

export default function TwoRowData(props) {
  return (
  <TableRow sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
    <TableCell component="th" scope="row">{props.label}</TableCell>
    <TableCell align="left">{props.value}</TableCell>
  </TableRow>
  )
}