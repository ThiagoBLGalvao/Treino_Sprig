import React from 'react';
import { Link } from 'react-router-dom';
import { Button, makeStyles } from '@material-ui/core';

import "./styles.css"

const useStyles = makeStyles({
    button:{
        minWidth:100
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
                <Link to="/" style={{ textDecoration: "none" }}>
                    <Button color="primary" className = {classes.button} variant="contained">
                        Home
                    </Button>
                </Link>
                <Button color="primary" className = {classes.button} variant="contained" onClick={() => handleChangeState(1)}>
                    List
                </Button>
            </div>
        </div>
    )
}