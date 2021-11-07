import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import { ThemeProvider } from 'styled-components';
import { GlobalProvider } from './context/GlobalContext';
import 'bootstrap/dist/css/bootstrap.min.css';

const globalTheme = {
  fontFamily: 'Varela Round, sans-serif'
}

ReactDOM.render(
  <React.StrictMode>
    <ThemeProvider theme={globalTheme}>
      <GlobalProvider>
        <App />
      </GlobalProvider>
    </ThemeProvider>
  </React.StrictMode>,
  document.getElementById('root')
);
