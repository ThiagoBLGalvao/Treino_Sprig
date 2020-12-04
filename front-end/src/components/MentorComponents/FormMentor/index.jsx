import { TextField } from '@material-ui/core';
import React, { useState } from 'react';

import api from '../../../services/api';
import Form from '../../Form';

import "./styles.css";


export default function FormMentor({ mentorUpdate, backToUpdate }) {
    const [name, setName] = useState("");

    function handleSubmit() {
        if (!(Object.keys(mentorUpdate).length > 1)) {
            handleCreateMentor();
        } else {
            handleUpdate();
        }
    }

    async function handleCreateMentor() {
        console.log(mentorUpdate);
        const data = {
            name
        };
        try {
            await api.post('mentor', data).then(response => alert('Mentor: ' + name + ' created'));
        } catch (err) {
            alert('Error creating an Mento!!')
        }
    }

    async function handleUpdate() {
        const data = {
            name
        };

        await api.put(`mentor/${mentorUpdate.id}`, data)
            .then(backToUpdate({}, 2));
    }

    function getButtonText() {
        return Object.keys(mentorUpdate).length > 1 ? "Update" : "Create"
    }

    return (
        <div className="contentForm">
            <Form
                handleSubmit={handleSubmit}
                action={getButtonText()}
            >
                <TextField
                    id="name"
                    name="name"
                    label="Name"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
            </Form>
        </div>
    );
}