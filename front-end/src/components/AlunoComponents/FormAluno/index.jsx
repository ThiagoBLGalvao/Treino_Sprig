import React, { useEffect, useState } from 'react';
import { FormControl, InputLabel, makeStyles, MenuItem, Select, TextField } from '@material-ui/core';
import Form from '../../Form';

import api from '../../../services/api';

import "./styles.css";
import ContentForm from '../../ContentForm';

const useStyles = makeStyles((theme) => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
    selectEmpty: {
        marginTop: theme.spacing(2),
    },
}));


export default function FormAluno({ alunoUpdate, backToUpdate }) {
    const [name, setName] = useState("");
    const [classMate, setClassMate] = useState("");
    const [mentor_id, setMentorId] = useState("");
    const [programa_id, setProgramaId] = useState("");
    const [mentorData, setMentorData] = useState([]);
    const [programaData, setProgramaData] = useState([]);
    const classes = useStyles();

    useEffect(() => {
        api.get('mentor/all').then(response => {
            setMentorData(response.data);
        });
        api.get('programa/all').then(response => {
            setProgramaData(response.data);
        });
    }, []);

    function handleSubmit() {
        if (!(Object.keys(alunoUpdate).length > 1)) {
            handleCreateAluno();
        } else {
            handleUpdate();
        }
    }

    async function handleCreateAluno() {
        console.log(alunoUpdate);
        const data = {
            name,
            classMate,
            mentor_id,
            programa_id
        };
        try {
            await api.post('aluno', data).then(response => alert('Aluno: ' + name + ' created'));
        } catch (err) {
            alert('Error creating an Mento!!')
        }
    }

    async function handleUpdate() {
        const data = {
            name,
            classMate,
            mentor_id,
            programa_id
        };

        await api.put(`aluno/${alunoUpdate.id}`, data)
            .then(backToUpdate({}, 2));
    }

    function getButtonText() {
        return Object.keys(alunoUpdate).length > 1 ? "Update" : "Create";
    }

    return (
        <ContentForm>
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
                <TextField
                    id="classMate"
                    name="classMate"
                    label="Classmate"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={classMate}
                    onChange={e => setClassMate(e.target.value)}
                />
                <div className="dataCollectorContainer">
                    <FormControl className={classes.formControl}>
                        <InputLabel id="programa_id">Programa</InputLabel>
                        <Select
                            labelId="programa_id"
                            id="programa_id"
                            value={programa_id}
                            onChange={(e) => setProgramaId(e.target.value)}
                        >
                            <MenuItem value=""><em>Programa</em> </MenuItem>
                            {programaData.map(programa => (
                                <MenuItem key={programa.id} value={programa.id}>{programa.name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <FormControl className={classes.formControl}>
                        <InputLabel id="mentor_id">Mentor</InputLabel>
                        <Select
                            labelId="mentor_id"
                            id="mentor_id"
                            value={mentor_id}
                            onChange={(e) => setMentorId(e.target.value)}
                        >
                            {mentorData.map(mentor => (
                                <MenuItem key={mentor.id} value={mentor.id}>{mentor.name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </div>
            </Form>
        </ContentForm>
    );
}