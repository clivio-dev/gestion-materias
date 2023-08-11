// App.js

import React, { useState } from 'react';
import Login from './components/Login';
import CarreraSelector from './components/CarreraSelector';
import MateriasTree from './components/MateriasTree';
import data from './mocks/departamentos.json'; 

const App = () => {
  const [user, setUser] = useState(null);
  const [selectedCarrera, setSelectedCarrera] = useState('');

  const handleLogin = (userData) => {
    // Aquí puedes implementar la lógica de verificación del inicio de sesión
    // Por ahora, simplemente establecemos el usuario con los datos proporcionados
    setUser(userData);
  };

  const handleSelectCarrera = (carreraCod) => {
    setSelectedCarrera(carreraCod);
  };

  return (
    <div className = "App">
      {!user ? (
        <Login onLogin={handleLogin} />
      ) : (
        <>
          <h1>Bienvenido, {user.username}!</h1>
          <CarreraSelector
            carreras={data.departamentos}
            onSelectCarrera={handleSelectCarrera}
          />
          {selectedCarrera && (
            <MateriasTree
              materias={data.departamentos
                .find((depto) => depto.cod === selectedCarrera)
                .planes[0].materias} // Solo mostramos las materias del primer plan de estudio
            />
          )}
        </>
      )}
    </div>
  );
};

export default App;
