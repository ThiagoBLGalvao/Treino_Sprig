import {Typography } from '@material-ui/core';
import React, { useState } from 'react';
import FormAvaliacao from '../../components/AvaliacaoComponent/FormAvaliacao';
import ListOfAvaliacao from '../../components/AvaliacaoComponent/ListOfAvaliacao';
import ManagementComponent from '../../components/ManagementComponent';

export default function ManagementMateria() {
    const [currentState, setCurrentState] = useState(0);
    const [avaliacaoToUpdate, setAvaliacaoToUpdate] = useState({});

    function backToUpdate(user, page) {
        setAvaliacaoToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormAvaliacao avaliacaoUpdate={avaliacaoToUpdate} backToUpdate={backToUpdate} />,
        <ListOfAvaliacao backToUpdate={backToUpdate} />,
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