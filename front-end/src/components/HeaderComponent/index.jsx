import { Button, makeStyles } from '@material-ui/core';
import React from 'react';
import { Link } from 'react-router-dom';
import { Home } from '@material-ui/icons'
import "./styles.css";

const useStyles = makeStyles({
    button:{
        width:60,
        color:"#000",
        marginRight:"2rem",
        '&:hover':{
            backgroundColor:"#fcbf1e",
            color:"#000"
        }
    }
})

export default function HeaderComponent() {

    const classes = useStyles();

    return (
        <header className="headerSystem">
            <h1>System School</h1>
            <Link to="/" style={{ textDecoration: "none" }}>
                <Button color="primary" className={classes.button} variant="contained">
                    <Home />
                </Button>
            </Link>
        </header>
    );
}