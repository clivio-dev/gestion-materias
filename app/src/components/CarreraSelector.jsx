// CarreraSelector.js

import React from 'react';

const CarreraSelector = ({ carreras, onSelectCarrera }) => {
  return (
    <div className="carrera-selector">
      <h2>Selecciona un Plan de Estudio</h2>
      <select onChange={(e) => onSelectCarrera(e.target.value)}>
        <option value="">-- Seleccionar Carrera --</option>
        {carreras.map((carrera) => (
          <option key={carrera.cod} value={carrera.cod}>
            {carrera.nombre}
          </option>
        ))}
      </select>
    </div>
  );
};

export default CarreraSelector;
