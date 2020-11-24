import { Button, Typography } from '@material-ui/core';
import React from 'react';
import { Link } from 'react-router-dom';

import './styles.css'

export default function LinesContent({text, link}){
    
    
    return(
        <div className="line-content">
                <Typography variant="h3" component="h1">Gerenciar {text}</Typography>
                <Link to= {link} style = {{textDecoration: "none"}}>
                    <Button variant = "contained" color = "primary">
                        Ir gerenciar {text}
                    </Button>
                </Link>
            </div>
    );
}