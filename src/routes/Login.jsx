import Header from '../sections/Header'
import Footer from '../sections/Footer'
import Form from '../components/Form'
import FormField from '../components/FormField'
import useAuth from '../components/useAuth'
import { useNavigate } from 'react-router-dom'

import '../styles/routes/Login.css'

const Login = () => {
  const auth = useAuth()
  const navigate = useNavigate()

  const handleSubmit = async (event) => {
    event.preventDefault()

    const user = event.target.elements.user_name.value
    const password = event.target.elements.password.value

    const result = await auth.signin(user, password)
    console.log(result)

    if (result.success) 
      navigate("/subjects")
        
  }

  return (
    <>
      <Header />
      <section className='login'>
        <Form onSubmit={handleSubmit}>
          <FormField
            label={{ htmlFor: "user_name", text: "Nombre de usuario" }}
            input={{ type: "text", autoComplete: "username", required: true }}
          />
          <FormField
            label={{ htmlFor: "password", text: "Contraseña" }}
            input={{ type: "password", required: true}}
          />
          <FormField>
            <button className='form__field__btn' type='submit'>Enviar</button>
          </FormField>
        </Form>
      </section>
      <Footer />
    </>
  )
}

export default Login