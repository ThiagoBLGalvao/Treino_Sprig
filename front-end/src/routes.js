import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import MainMenu from './Pages/MainPage';
import ManagementMentor from './Pages/Mentor/ManagementMentor';

export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path = "/" exact component = {MainMenu} />
                <Route path = "/managementMentor" component = {ManagementMentor}/>
            </Switch>
        </BrowserRouter>
    );
}