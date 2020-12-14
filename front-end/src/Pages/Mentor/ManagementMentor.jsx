import { Typography } from '@material-ui/core';
import React, { useState } from 'react';

import ManagementComponent from '../../components/ManagementComponent';
import FormMentor from '../../components/MentorComponents/FormMentor';
import ListOfMentors from '../../components/MentorComponents/ListOfMentors';

export default function ManagementMentor() {
    const [currentState, setCurrentState] = useState(0);
    const [userToUpdate, setUserToUpdate] = useState({});

    function backToUpdate(user, page) {
        setUserToUpdate(user);
        setCurrentState(page);
    }

    function handleChangeCurrentState(state) {
        setCurrentState(state);
    }

    const states = [
        <FormMentor mentorUpdate={userToUpdate} backToUpdate={backToUpdate} />,
        <ListOfMentors backToUpdate={backToUpdate} />,
        <Typography variant="h3" component="h1">Update Successful</Typography>
    ];

    return (
        <ManagementComponent handleChangeState={handleChangeCurrentState}>
            {
                states[currentState]
            }
        </ManagementComponent>
    );
}