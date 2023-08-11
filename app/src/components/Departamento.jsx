/* 
    cod supongo que es un string
    planes supongo que es un array
    nombre supongo que es un string
    
    const dep_data = {
        cod: '123',
        planes: ['Informatica','Civil'],
        nombre: 'Ingenieria'
    }
*/

//Departamento -> dpto

const Departamento = ({ cod, planes, nombre }) => {
    return(
        <div className="dpto">
            <h2 className="dpto-titulo">
                Departamento de 
                <a 
                    className="dpto-link"
                    href={`http://https://${nombre}.unlam.edu.ar/index.php`}  
                    target="_blank" rel="noopener noreferrer"
                >
                    {nombre}
                </a>
            </h2>
            <p className="dpto-cod">
                {codigo}
            </p>
            <ul className="dpto-planes">
                {planes.forEach((plan, i) => {
                    return(
                        <li 
                            className="dpto-plan"    
                            key = {i}
                        >
                            {plan}
                        </li>
                    )
                })}
            </ul>
        </div>
    );    
}

export default Departamento;