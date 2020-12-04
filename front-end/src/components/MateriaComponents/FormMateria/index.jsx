import React, { useState } from 'react';
import { TextField } from '@material-ui/core';
import Form from '../../Form';

import api from '../../../services/api';

import "./styles.css";


export default function FormMateria({ materiaUpdate, backToUpdate }) {
    const [name, setName] = useState("");

    function handleSubmit() {
        if (!(Object.keys(materiaUpdate).length > 1)) {
            handleCreateMateria();
        } else {
            handleUpdate();
        }
    }

    async function handleCreateMateria() {
        console.log(materiaUpdate);
        const data = {
            name
        };
        try {
            await api.post('materia', data).then(response => alert('Materia: ' + name + ' created'));
        } catch (err) {
            alert('Error creating an Mento!!')
        }
    }

    async function handleUpdate() {
        const data = {
            name
        };

        await api.put(`materia/${materiaUpdate.id}`, data)
            .then(backToUpdate({}, 2));
    }

    function getButtonText() {
        return Object.keys(materiaUpdate).length > 1 ? "Update" : "Create"
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