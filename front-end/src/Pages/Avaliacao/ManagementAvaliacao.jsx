import { Button, Typography } from '@material-ui/core';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import FormAvaliacao from '../../components/AvaliacaoComponent/FormAvaliacao';
import ListOfAvaliacao from '../../components/AvaliacaoComponent/ListOfAvaliacao';
import "./styles.css"

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

    return (
        <div className="managementContainer">
            {
                states[currentState]
            }
            <div className="stateContainer">
                <Button variant="contained" onClick={() => setCurrentState(0)}>
                    Create
                </Button>
                <Link to="/" style={{ textDecoration: "none" }}>
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