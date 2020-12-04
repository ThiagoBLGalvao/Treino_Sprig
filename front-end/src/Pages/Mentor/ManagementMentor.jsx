import { Button, Typography } from '@material-ui/core';
import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import FormMentor from '../../components/MentorComponents/FormMentor';
import ListOfMentors from '../../components/MentorComponents/ListOfMentors';
import "./styles.css"

export default function ManagementMentor() {
    const [currentState, setCurrentState] = useState(0);
    const [userToUpdate, setUserToUpdate] = useState({});

    function backToUpdate(user, page) {
        setUserToUpdate(user);
        setCurrentState(page);
    }
    const states = [
        <FormMentor mentorUpdate={userToUpdate} backToUpdate={backToUpdate} />,
        <ListOfMentors backToUpdate={backToUpdate} />,
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