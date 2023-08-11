// MateriasTree.js

import React from 'react';

const MateriasTree = ({ materias }) => {
  const renderMateria = (materia) => {
    return (
      <li key={materia.id}>
        {materia.nombre}
        {materia.correlativas.length > 0 && (
          <ul>
            {materia.correlativas.map((correlativa) => (
              <li key={correlativa}>{correlativa}</li>
            ))}
          </ul>
        )}
      </li>
    );
  };

  return (
    <div className="materias-tree">
      <h2>Árbol de Progreso de Materias</h2>
      <ul>
        {materias.map((materia) => renderMateria(materia))}
      </ul>
    </div>
  );
};

export default MateriasTree;
