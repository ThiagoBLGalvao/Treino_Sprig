import { Typography } from '@material-ui/core';
import React, { useState } from 'react';
import ManagementComponent from '../../components/ManagementComponent';
import FormPrograma from '../../components/ProgramaComponents/FormPrograma' ;
import ListOfPrograma from '../../components/ProgramaComponents/ListOfProgramas';



export default function ManagementPrograma() {
    const [currentState, setCurrentState] = useState(0);
    const [programaToUpdate, setProgramaToUpdate] = useState({});

    function backToUpdate(user, page) {
        setProgramaToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormPrograma programaUpdate = {programaToUpdate} backToUpdate = {backToUpdate} />,
        <ListOfPrograma backToUpdate = {backToUpdate} />,
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