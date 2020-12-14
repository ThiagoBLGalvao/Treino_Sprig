import React from 'react';
import "./styles.css";

export default function ContentForm({children}){
    return(
        <div className = "formContent">
            {
                children
            }
        </div>
    )   
}