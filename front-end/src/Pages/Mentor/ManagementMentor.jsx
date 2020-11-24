import { Button } from '@material-ui/core';
import React, { useState } from 'react';
import FormMentor from '../../components/FormMentor';
import ListOfMentors from '../../components/ListOfMentors';
import "./styles.css"

export default function ManagementMentor() {
    const [currentState, setCurrentState] = useState(0);
    const [userToUpdate, setUserToUpdate] = useState({});

    function backToUpdate(user){
        setUserToUpdate(user);
        setCurrentState(0);
    }
    const states = [
        <FormMentor mentorUpdate = {userToUpdate}/>,
        <ListOfMentors backToUpdate = {backToUpdate}/>
    ];

    return (
        <div className="managementContainer">
            {
                states[currentState]
            }
            <div className="stateContainer">
                <Button variant="contained" onClick= {()=> setCurrentState(0)}>
                    Create
                </Button>
                <Button variant="contained" onClick= {()=> setCurrentState(1)}>
                    List
                </Button>                
            </div>
        </div>
    );
}