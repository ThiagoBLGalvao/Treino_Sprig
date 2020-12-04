import { Button, Typography } from '@material-ui/core';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import FormPrograma from '../../components/ProgramaComponents/FormPrograma' ;
import ListOfPrograma from '../../components/ProgramaComponents/ListOfProgramas';

import "./styles.css";

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