import { Button, makeStyles} from '@material-ui/core';
import React from 'react';
import { Link } from 'react-router-dom';

import './styles.css';

const useStyles = makeStyles({
    button:{
        minWidth: 300
    }
});

export default function LinesContent({text, link}){
    const classes = useStyles();
    
    return(
        <div className="line-content">
                <Link to= {link} style = {{textDecoration: "none"}}>
                    <Button className = {classes.button} variant = "contained" color = "primary">
                        {text}
                    </Button>
                </Link>
            </div>
    );
}