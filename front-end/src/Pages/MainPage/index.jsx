import React from 'react';

import 'fontsource-roboto';
import './styles.css';
import LinesContent from '../../components/LinesContent/LineContent';

export default function MainMenu() {
    return (
        <div className="MainMenuContainer">
            <div className="content">
                <LinesContent text="Aluno" link="/managementAluno" />
                <LinesContent text="Mentor" link="/managementMentor" />
                <LinesContent text="Materia" link="/managementMateria" />
                <LinesContent text="Programa" link="/managementPrograma" />
                <LinesContent text="Avalição" link="/managementAvaliacao" />
            </div>
        </div>
    );
}