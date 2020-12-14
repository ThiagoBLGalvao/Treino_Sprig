import { Typography } from '@material-ui/core';
import React, { useState } from 'react';
import FormAluno from '../../components/AlunoComponents/FormAluno';
import ListOfAluno from '../../components/AlunoComponents/ListOfAlunos';
import ManagementComponent from '../../components/ManagementComponent';

export default function ManagementAluno() {
    const [currentState, setCurrentState] = useState(0);
    const [alunoToUpdate, setAlunoToUpdate] = useState({});

    function backToUpdate(user, page) {
        setAlunoToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormAluno alunoUpdate={alunoToUpdate} backToUpdate={backToUpdate} />,
        <ListOfAluno backToUpdate={backToUpdate} />,
        <Typography variant="h3" component="h1">Update Successful</Typography>
    ];

    function handleChangeCurrentState(state) {
        setCurrentState(state);
    }

    return (
        <ManagementComponent handleChangeState={handleChangeCurrentState}>
            {
                states[currentState]
            }
        </ManagementComponent>
    );
}