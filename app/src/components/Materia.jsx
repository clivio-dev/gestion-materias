/*
    id supongio que es un string
    nombre supongo que es un string
    correlativas supongo que es un array de string con los nombres

    const materia = {
        id: '123',
        nombre: 'algebra',
        correlativas: ['']
    }
*/

const Materia = ({ id, nombre, correlativas }) => {
    return(
        <div className="mat">
            <h2 className="mat-titulo">
                {nombre}
            </h2>
            <p className="mat-id">
                {id}
            </p>
            {
                Array.isArray(correlativas) &&
                correlativas.length > 0 &&
                <ul className="mat-correlativas">
                    {correlativas.forEach((materia, i) => {
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

export default Materia;