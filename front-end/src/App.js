import React from 'react';
import Routes from './routes';
import { ThemeProvider, createMuiTheme } from '@material-ui/core'
import "./global.css";

const theme = createMuiTheme({
   palette: {
      type: 'dark', 
      primary: { 
        main: "#c0c0c0" 
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
      <Routes />
    </ThemeProvider>
  );
}

export default App;
