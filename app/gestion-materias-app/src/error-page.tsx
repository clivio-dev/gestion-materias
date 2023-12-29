import { useRouteError } from "react-router-dom"

interface RouteError{
    statusText: string,
    message: string
}

const ErrorPage = () => {
    const error = useRouteError() as RouteError;
    console.log(error);
    
    return(
        <div id="error-page">
            <h1>Lo siento!</h1>
            <p>Un error inesperado ha ocurrido.</p>
            <p>
                <i>{error.statusText || error.message}</i>
            </p>
        </div>
    )
}   

export default ErrorPage;