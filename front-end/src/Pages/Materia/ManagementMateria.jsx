import {Typography } from '@material-ui/core';
import React, { useState } from 'react';
import ManagementComponent from '../../components/ManagementComponent';
import FormMateria from '../../components/MateriaComponents/FormMateria';
import ListOfMateria from '../../components/MateriaComponents/ListOfMateria';

export default function ManagementMateria() {
    const [currentState, setCurrentState] = useState(0);
    const [materiaToUpdate, setMateriaToUpdate] = useState({});

    function backToUpdate(user, page) {
        setMateriaToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormMateria materiaUpdate={materiaToUpdate} backToUpdate={backToUpdate} />,
        <ListOfMateria backToUpdate={backToUpdate} />,
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