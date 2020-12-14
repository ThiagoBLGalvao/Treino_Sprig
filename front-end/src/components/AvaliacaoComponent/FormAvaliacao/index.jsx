import React, { useEffect, useState } from 'react';
import { FormControl, InputLabel, makeStyles, MenuItem, Select, TextField } from '@material-ui/core';
import Form from '../../Form';

import api from '../../../services/api';

import "./styles.css";
import { DatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
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


export default function FormAluno({ avaliacaoUpdate, backToUpdate }) {
    const [mesDataType, setMesDataType] = useState(new Date());
    const [nota, setNota] = useState(0);
    const [mentor_id, setMentorId] = useState("");
    const [materia_id, setMateriaId] = useState("");
    const [aluno_id, setAlunoId] = useState("");
    const [mentorData, setMentorData] = useState([]);
    const [materiaData, setMateriaData] = useState([]);
    const [alunoData, setAlunoData] = useState([]);
    const classes = useStyles();

    useEffect(() => {
        api.get('mentor/all').then(response => {
            setMentorData(response.data);
        });
        api.get('materia/all').then(response => {
            setMateriaData(response.data);
        });
        api.get('aluno/all').then(response => {
            setAlunoData(response.data);
        });
    }, []);

    function handleSubmit() {
        if (!(Object.keys(avaliacaoUpdate).length > 1)) {
            handleCreateAavaliacao();
        } else {
            handleUpdate();
        }
    }

    async function handleCreateAavaliacao() {
        var mes = mesDataType.getMonth();
        const data = {
            nota,
            mes,
            mentor_id,
            materia_id,
            aluno_id
        };
        console.log(data);
        try {
            await api.post('avaliacao', data).then(response => alert('Avaliacao created to moth: ' + response.data.mes));
        } catch (err) {
            alert('Error creating an Mento!!')
        }
    }

    async function handleUpdate() {
        var mes = mesDataType.getMonth();
        const data = {
            mes,
            nota,
            mentor_id,
            materia_id,
            aluno_id
        };

        await api.put(`avaliacao/${avaliacaoUpdate.id}`, data)
            .then(backToUpdate({}, 2));
    }

    function getButtonText() {
        return Object.keys(avaliacaoUpdate).length > 1 ? "Update" : "Create";
    }

    return (
        <ContentForm>
            <Form
                handleSubmit={handleSubmit}
                action={getButtonText()}
            >
                <TextField
                    id="nota"
                    name="nota"
                    label="Nota"
                    variant="outlined"
                    fullWidth
                    margin="normal"
                    value={nota}
                    onChange={e => setNota(e.target.value)}
                />
                <div className="dataCollectorContainer">
                    <FormControl className={classes.formControl}>
                        <InputLabel id="aluno_id">Aluno</InputLabel>
                        <Select
                            labelId="aluno_id"
                            id="aluno_id"
                            value={aluno_id}
                            onChange={(e) => setAlunoId(e.target.value)}
                        >
                            {alunoData.map(aluno => (
                                <MenuItem key={aluno.id} value={aluno.id}>{aluno.name}</MenuItem>
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
                    <FormControl className={classes.formControl}>
                        <InputLabel id="materia_id">Materia</InputLabel>
                        <Select
                            labelId="materia_id"
                            id="materia_id"
                            value={materia_id}
                            onChange={(e) => setMateriaId(e.target.value)}
                        >
                            {materiaData.map(materia => (
                                <MenuItem key={materia.id} value={materia.id}>{materia.name}</MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                </div>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <div className="dataCollectorContainer">
                        <DatePicker
                            label="Mês"
                            views={["month"]}
                            format="MMMM"
                            inputVariant="outlined"
                            value={mesDataType}
                            ToolbarComponent={() => null}
                            openTo="month"
                            onChange={setMesDataType}
                        />
                    </div>
                </MuiPickersUtilsProvider>
            </Form>
        </ContentForm>
    );
}