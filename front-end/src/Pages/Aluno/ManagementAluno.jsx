import { Button, Typography } from '@material-ui/core';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import FormAluno from '../../components/AlunoComponents/FormAluno';
import ListOfAluno from '../../components/AlunoComponents/ListOfAlunos';
import "./styles.css"

export default function ManagementAluno() {
    const [currentState, setCurrentState] = useState(0);
    const [alunoToUpdate, setAlunoToUpdate] = useState({});

    function backToUpdate(user, page) {
        setAlunoToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormAluno alunoUpdate = {alunoToUpdate} backToUpdate = {backToUpdate} />,
        <ListOfAluno backToUpdate = {backToUpdate} />,
        <Typography variant="h3" component="h1">Update Successful</Typography>
    ];

    return (
        <div className="managementContainer">
            {
                states[currentState]
            }
            <div className="stateContainer">
                <Button variant="contained" onClick={() => setCurrentState(0)}>
                    Create
                </Button>
                <Link to="/" style = {{textDecoration:"none"}}>
                    <Button variant="contained">
                        Home
                    </Button>
                </Link>
                <Button variant="contained" onClick={() => setCurrentState(1)}>
                    List
                </Button>
            </div>
        </div>
    );
}