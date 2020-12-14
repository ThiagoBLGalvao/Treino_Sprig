import React from 'react';
import Routes from './routes';
import { ThemeProvider, createMuiTheme } from '@material-ui/core'
import "./global.css";
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';

const theme = createMuiTheme({
   palette: {
      type: 'dark', 
      primary: { 
        main: "#40bad5" 
      } 
    },
    typography: {
      h3:{
        color:"#FFF"
      }    
    }
  });

function App() {
  return (
    <ThemeProvider theme = {theme}>
      <HeaderComponent />
      <Routes />
      <FooterComponent/>
    </ThemeProvider>
  );
}

export default App;
