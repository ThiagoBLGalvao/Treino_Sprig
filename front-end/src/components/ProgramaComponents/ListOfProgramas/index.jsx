import { Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import api from '../../../services/api';
import TableList from '../../TableList';

import "./styles.css";


const useStyles = makeStyles({
    table: {
        minWidth: 650
    }
});


export default function ListOfPrograma({ backToUpdate }) {
    const [programaResponse, setProgramaResponse] = useState([]);
    const [actualPage, setActualPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const classes = useStyles();

    useEffect(() => {
        api.get(`programa?linesPerPage=3&page=${actualPage}`)
            .then(response => {
                console.log(response);
                setProgramaResponse(response.data.content);
                setTotalPages(response.data.totalPages);
            });

    }, [actualPage]);

    async function handleDelete(id) {
        api.delete(`programa/${id}`)
            .then(setProgramaResponse(programaResponse.filter((element) => element.id !== id)));
    }

    return (
        <TableList>

            <TableContainer component={Paper}>
                <Table className={classes.table}>
                    <TableHead>
                        <TableRow>
                            <TableCell align="center">
                                Name
                        </TableCell>
                            <TableCell align="center">
                                Data of Begining
                        </TableCell>
                            <TableCell align="center">
                                Date of Ending
                        </TableCell>
                            <TableCell align="right">
                                Actions
                        </TableCell>
                            <TableCell>

                            </TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {programaResponse.map(response => (
                            <TableRow key={response.id}>
                                <TableCell align="center">
                                    {response.name}
                                </TableCell>
                                <TableCell align="center">
                                    {new Date(response.beginningDate * 1000).toLocaleString()}
                                </TableCell>
                                <TableCell align="center">
                                    {new Date(response.endingDate * 1000).toLocaleString()}
                                </TableCell>
                                <TableCell align="center">
                                    <Button onClick={() => backToUpdate(response, 0)}>Alter</Button>
                                </TableCell>
                                <TableCell align="center">
                                    <Button onClick={() => handleDelete(response.id)}>X</Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
                <div className="paginationButtons">
                    <Button disabled={actualPage === 0} onClick={() => setActualPage(actualPage - 1)}>
                        {"<"}
                    </Button>
                    <Button disabled={actualPage === totalPages - 1} onClick={() => setActualPage(actualPage + 1)}>
                        {">"}
                    </Button>
                </div>
            </TableContainer>
        </TableList>
    );
}
