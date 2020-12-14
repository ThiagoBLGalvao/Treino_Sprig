import React from 'react';
import "./styles.css";

export default function TableList({children}){
    return(
        <div className = "tableContainer">
            {
                children
            }
        </div>
    );
}