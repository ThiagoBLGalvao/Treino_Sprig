import React from 'react';
import { Button, makeStyles } from '@material-ui/core';

import "./styles.css"

const useStyles = makeStyles({
    button:{
        minWidth:100,
        height:30,
        color:"#000",
        '&:hover':{
            backgroundColor:"#fcbf1e",
            color:"#000"
        }
    }
})

export default function ManagementComponent({ children, handleChangeState }) {
    
    const classes = useStyles();

    return (
        <div className="managementContainer">

            {
                children
            }

            <div className="stateContainer">
                <Button color="primary" className = {classes.button} variant="contained" onClick={() => handleChangeState(0)}>
                    Create
                </Button>
                <Button color="primary" className = {classes.button} variant="contained" onClick={() => handleChangeState(1)}>
                    List
                </Button>
            </div>
        </div>
    )
}