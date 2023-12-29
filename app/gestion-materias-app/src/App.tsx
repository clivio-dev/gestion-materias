import './App.css'
import Nav from './components/Nav'

function App() {
  return (
    <>
      <Nav
        home={{id: "#home", content: "Inicio"}}
        routes={[
          {url: "login/", content: "Iniciar Sesión"}
        ]}
        footer={{id: "#contact", content: "Contacto"}}
      />
      <header>
        <h1>Gestión de Materias</h1>
        <p>Organiza tu plan de estudios</p>
      </header>
      <main>
        {/*Explicar porque el usuario debería registrarse*/}
      </main>
      <footer>
        {/*Incluir un componente de redes o contacto*/}
      </footer>
    </>
  )
}

export default App
