import React, { useEffect, useState } from 'react';
import { Button, makeStyles, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow, withStyles } from '@material-ui/core';
import { DeleteForever, Edit } from '@material-ui/icons';
import api from '../../../services/api';
import TableList from '../../TableList';

import "./styles.css";


const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: "#2b2b2b",
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {

        backgroundColor: "#808080",

    },
}))(TableRow);

const useStyles = makeStyles({
    table: {
        minWidth: 650
    },
    tableCell: {
        display: "flex",
        justifyContent: "space-around",
    },
    button: {
        '&:hover': {
            backgroundColor: "#fcbf1e",
            color: "#000"
        }
    },
    buttonDelete: {
        '&:hover': {
            backgroundColor: "#fcbf1e",
            color: "#f05454"
        }
    },
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
                        <StyledTableRow>
                            <StyledTableCell align="center">
                                Name
                            </StyledTableCell>
                            <StyledTableCell align="center">
                                Data of Begining
                            </StyledTableCell>
                            <StyledTableCell align="center">
                                Date of Ending
                            </StyledTableCell>
                            <StyledTableCell align="center">
                                Actions
                            </StyledTableCell>
                        </StyledTableRow>
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
                                <TableCell className={classes.tableCell} align="center">
                                    <Button className={classes.button} onClick={() => backToUpdate(response, 0)}>
                                        <Edit/>
                                    </Button>
                                
                                    <Button className={classes.buttonDelete} onClick={() => handleDelete(response.id)}>
                                        <DeleteForever/>
                                    </Button>
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
