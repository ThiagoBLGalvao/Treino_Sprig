// import { Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
// import React, { useEffect, useState } from 'react';
// import api from '../../../services/api';

// import "./styles.css";


// const useStyles = makeStyles({
//     table: {
//         minWidth: 650
//     }
// });


// export default function ListOfMentors({backToUpdate, listAll}) {
//     const [entityResponse, setEntityResponse ] = useState([]);
//     const [actualPage, setActualPage] = useState(0);
//     const [totalPages, setTotalPages] = useState(0);

//     const classes = useStyles();

//     useEffect(() => {
//         console.log(listAll(actualPage));
//         setEntityResponse(listAll(actualPage));
//     }, [actualPage]);

//     async function handleDelete(id){
//         api.delete(`mentor/${id}`)
//         .then(setEntityResponse(entityResponse.filter((element)=> element.id !== id)));
//     }


//     return (
//         <TableContainer component={Paper}>
//             <Table className={classes.table}>
//                 <TableHead>
//                     <TableRow>
//                         <TableCell align="center">
//                             Name
//                         </TableCell>
//                         <TableCell align="right">
//                             Actions
//                         </TableCell>
//                     </TableRow>
//                 </TableHead>
//                 <TableBody>
//                     {entityResponse.map(response =>(
//                         <TableRow key={response.id}>
//                             <TableCell align="center">
//                                 {response.name}
//                             </TableCell>
//                             <TableCell align="center">
//                                 <Button onClick = {()=> backToUpdate(response, 0)}>Alter</Button>
//                             </TableCell>
//                             <TableCell align="center">
//                                 <Button onClick = {() => handleDelete(response.id)}>X</Button>
//                             </TableCell>
//                         </TableRow>
//                     ))}
//                 </TableBody>
//             </Table>
//             <div className = "paginationButtons">
//                 <Button disabled = {actualPage === 0} onClick={() => setActualPage(actualPage - 1)}>
//                     {"<"}
//                 </Button>
//                 <Button disabled = {actualPage === totalPages -1} onClick={() => setActualPage(actualPage + 1)}>
//                     {">"}
//                 </Button>
//             </div>
//         </TableContainer>
//     );
// }
