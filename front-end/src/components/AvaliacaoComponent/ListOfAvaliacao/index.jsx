import React, { useEffect, useState } from 'react';
import { Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from '@material-ui/core';
import api from '../../../services/api';

import "./styles.css";


const useStyles = makeStyles({
    table: {
        minWidth: 650
    }
});


export default function ListOfMentors({ backToUpdate }) {
    const [AvaliacaoResponse, setAvaliacaoResponse] = useState([]);
    const [actualPage, setActualPage] = useState(0);
    const [totalPages, setTotalPages] = useState(0);

    const classes = useStyles();

    useEffect(() => {
        api.get(`avaliacao?linesPerPage=5&page=${actualPage}`)
            .then(response => {
                console.log(response);
                setAvaliacaoResponse(response.data.content);
                setTotalPages(response.data.totalPages);
            });
    }, [actualPage]);

    async function handleDelete(id) {
        api.delete(`aluno/${id}`)
            .then(setAvaliacaoResponse(AvaliacaoResponse.filter((element) => element.id !== id)));
    }

    return (
        <TableContainer component={Paper}>
            <Table className={classes.table}>
                <TableHead>
                    <TableRow>
                        <TableCell align="center">
                            Mes
                        </TableCell>
                        <TableCell align="center">
                            Nota
                        </TableCell>
                        <TableCell align="center">
                            Materia
                        </TableCell>
                        <TableCell align="center">
                            Aluno
                        </TableCell>
                        <TableCell align="right">
                            Actions
                        </TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {AvaliacaoResponse.map(response => (
                        <TableRow key={response.id}>
                            <TableCell align="center">
                                {response.mes}
                            </TableCell>
                            <TableCell align="center">
                                {response.nota}
                            </TableCell>
                            <TableCell align="center">
                                {response.materiaDto.name}
                            </TableCell>
                            <TableCell align="center">
                                {response.alunoDto.name}
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
    );
}
