import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import MainMenu from './Pages/MainPage';
import ManagementMentor from './Pages/Mentor/ManagementMentor';
import ManagementMateria from './Pages/Materia/ManagementMateria';
import ManagementPrograma from './Pages/Programa/ManagementPrograma';
import ManagementAluno from './Pages/Aluno/ManagementAluno';
import ManagementAvaliacao from './Pages/Avaliacao/ManagementAvaliacao';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

export default function Routes() {
    return (
        <BrowserRouter>
            <HeaderComponent />
            <Switch>
                <Route path="/" exact component={MainMenu} />
                <Route path="/managementMentor" component={ManagementMentor} />
                <Route path="/managementMateria" component={ManagementMateria} />
                <Route path="/managementPrograma" component={ManagementPrograma} />
                <Route path="/managementAluno" component={ManagementAluno} />
                <Route path="/managementAvaliacao" component={ManagementAvaliacao} />
            </Switch>
            <FooterComponent />
        </BrowserRouter>
    );
}