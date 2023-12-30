//import './App.css'
import Nav from './components/Nav'
import Footer from './components/Footer'
import { HOME, LOGIN, REGISTER, CONTACT, SOCIAL_FACEBOOK } from './constants'

function App() {
  return (
    <>
      <Nav
        home={{id: HOME.id, content: HOME.content}}
        routes={[
          {url: LOGIN.url, content: LOGIN.content},
          {url: REGISTER.url, content: REGISTER.content}
        ]}
        footer={{id: CONTACT.id, content: CONTACT.content}}
      />
      <header className='w-dvw flex align-middle justify-center'>
        <div className='my-5 p-5'>
          <h1 className='text-center'>Gestión de Materias</h1>
          <p className='text-center'>Organiza tu plan de estudios</p>
        </div>
      </header>
      <main id={HOME.id} className='h-dvh'>
        {/*Explicar porque el usuario debería registrarse*/}
      </main>
      <Footer
        id= {CONTACT.id}
        content= "En construcción... (footer)"
        SocialNetworks={
          [
            {url: SOCIAL_FACEBOOK.url, icon: SOCIAL_FACEBOOK.icon},
            {url: SOCIAL_FACEBOOK.url, icon: SOCIAL_FACEBOOK.icon},
            {url: SOCIAL_FACEBOOK.url, icon: SOCIAL_FACEBOOK.icon},
            {url: SOCIAL_FACEBOOK.url, icon: SOCIAL_FACEBOOK.icon}
            // agregar más redes..
          ]
        }
      />
    </>
  )
}

export default App
