import { Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, withStyles } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import api from '../../../services/api';
import TableList from '../../TableList';

import "./styles.css";

const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: "#035aa6",
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {

        backgroundColor: "#035aa6",

    },
}))(TableRow);

const useStyles = makeStyles({
    table: {
        minWidth: 650
    }
});


export default function ListOfMentors({ backToUpdate }) {
    const [AlunoResponse, setAlunoResponse] = useState([]);
    const [actualPage, setActualPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const classes = useStyles();

    useEffect(() => {
        api.get(`aluno?linesPerPage=5&page=${actualPage}`)
            .then(response => {
                console.log(response);
                setAlunoResponse(response.data.content);
                setTotalPages(response.data.totalPages);
            });

    }, [actualPage]);

    async function handleDelete(id) {
        api.delete(`aluno/${id}`)
            .then(setAlunoResponse(AlunoResponse.filter((element) => element.id !== id)));
    }


    return (
        <TableList>

            <TableContainer component={Paper}>
                <Table className={classes.table}>
                    <TableHead>
                        <StyledTableRow>
                            <StyledTableCell align="center">
                                Name
                            </StyledTableCell>
                            <StyledTableCell align="center">
                                Actions
                            </StyledTableCell>
                        </StyledTableRow>
                    </TableHead>
                    <TableBody>
                        {AlunoResponse.map(response => (
                            <StyledTableRow key={response.id}>
                                <StyledTableCell align="center">
                                    {response.name}
                                </StyledTableCell>
                                <StyledTableCell align="center">
                                    <Button onClick={() => backToUpdate(response, 0)}>Alter</Button>

                                    <Button onClick={() => handleDelete(response.id)}>X</Button>
                                </StyledTableCell>
                            </StyledTableRow>
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
