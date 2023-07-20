import React, { useState } from "react";
import data from "../mocks/departamentos.json"; // Importar el archivo JSON con los datos

const ArbolMaterias = () => {
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const [selectedPlan, setSelectedPlan] = useState(null);

  const handleDepartmentClick = (department) => {
    setSelectedDepartment(department);
    if(!department.planes.includes(selectedPlan))
      setSelectedPlan(null)
  };

  const handlePlanClick = (plan) => {
    setSelectedPlan(plan);
  };

  return (
    <div>
      <h1>Árbol de Materias</h1>
      <ul>
        {
          Array.isArray(data.departamentos) &&
          data.departamentos.length > 0 &&
          data.departamentos.map((departamento) => (
            <li 
              key={departamento.cod} 
              onClick={() => handleDepartmentClick(departamento)}
            >
              {departamento.nombre}
            </li>
          ))
        }
      </ul>
      {
        selectedDepartment && (
        <div>
          <h2>Departamento Seleccionado: {selectedDepartment.nombre}</h2>
          <h3>Planes de Estudio:</h3>
          <ul>
            {
              Array.isArray(selectedDepartment.planes) &&
              selectedDepartment.planes.length > 0 && 
              selectedDepartment.planes.map((plan) => (
                <li 
                  key={plan.cod}
                  onClick = {() => handlePlanClick(plan)}
                >
                  {plan.nombre}      
                </li>  
              ))
            }
          </ul>
        </div> )
      }
      {
        selectedPlan && (
        <div>
          <h3>Materias:</h3>
          <ul>
            {
              Array.isArray(selectedPlan.materias) &&
              selectedPlan.materias.length > 0 &&
              selectedPlan.materias.map((materia) => (
                <li
                  key={materia.id}
                >
                  {materia.nombre}
                </li>
              ))
            }
          </ul>
        </div>)
      }
    </div>
  );
};

export default ArbolMaterias;
