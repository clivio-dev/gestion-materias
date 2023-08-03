import Router from './Path/Router.js';
import Nav from './Componentes/Navbar/Nav.js';
import Inicio from './Vistas/Inicio.js';
import Materias from './Vistas/Materias.js';
import Carreras from './Vistas/Carreras.js';

function App() {
  return (
    <div className="main">
      <Nav />
      <Router path="/inicio">
        <Inicio />
      </Router>
      <Router path="/materias">
        <Materias />
      </Router>
      <Router path="/carreras">
        <Carreras />
      </Router>
    </div>
  );
}

export default App;
