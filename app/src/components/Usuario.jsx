/*
    id supongo que es un string
    nombre supongo que es un string
    materias aprobadas supongo que es un array de string de los id de materias

    const user = {
        id: '123'
        nombre: 'gonzalo'
        materiasAprob: ['223','333','111']
    }
*/

const Usuario = ({ id, nombre, materias }) => {
    return(
        <div>
            <h2 className="user-titulo">
                {nombre}
            </h2>
            <p className="user-id">
                {id}
            </p>
            {
                Array.isArray(materias) &&
                materias.length > 0 &&
                <ul className="user-materias">
                    {materias.forEach((materia, i) => {
                        return(
                            <li key={i}>
                                {materia}
                            </li>
                        );
                    })}
                </ul>
            }
        </div>
    );
}

export default Usuario;