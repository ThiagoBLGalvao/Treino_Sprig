import { TextField } from '@material-ui/core';
import { DateTimePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import React, { useState } from 'react';

import api from '../../../services/api';

import "./styles.css";
import Form from '../../Form';


export default function FormPrograma({ programaUpdate, backToUpdate }) {
    const [name, setName] = useState("");
    const [beginingDateType, handleBeginingDateChange] = useState(new Date());
    const [endingDateType, handleEndingDateChange] = useState(new Date());

    function handleSubmit() {
        if (!(Object.keys(programaUpdate).length > 1)) {
            handleCreatePrograma();
        } else {
            handleUpdate();
        }
    }

    async function handleCreatePrograma() {
        console.log(programaUpdate);
        const beginningDate = beginingDateType.toISOString();
        const endingDate = endingDateType.toISOString();
        const data = {
            name,
            beginningDate,
            endingDate
        };
        try {
            console.log(data);
            await api.post('programa', data).then(response => alert('Programa: ' + name + ' created'));
        } catch (err) {
            alert('Error creating an Mento!!')
        }
    }

    async function handleUpdate() {
        const beginningDate = beginingDateType.toISOString();
        const endingDate = endingDateType.toISOString();
        const data = {
            name,
            beginningDate,
            endingDate
        };

        await api.put(`programa/${programaUpdate.id}`, data)
            .then(backToUpdate({}, 2));
    }

    function getButtonText(){
        return Object.keys(programaUpdate).length > 1 ? "Update" : "Create"
    }

    return (
        <div className="contentForm">
            <Form
                handleSubmit = {handleSubmit}
                action = {getButtonText()}
            >
                <TextField
                    id="name"
                    name="name"
                    label="Program Name"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={name}
                    onChange={e => setName(e.target.value)}
                />
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <div className="dataCollectorContainer">
                        <DateTimePicker
                            label="Dia de Inicio"
                            inputVariant="outlined"
                            value={beginingDateType}
                            onChange={handleBeginingDateChange}
                        />
                        <DateTimePicker
                            label="Dia de fechamento"
                            inputVariant="outlined"
                            value={endingDateType}
                            onChange={handleEndingDateChange}
                        />
                    </div>
                </MuiPickersUtilsProvider>
            </Form>
        </div>
    );
}