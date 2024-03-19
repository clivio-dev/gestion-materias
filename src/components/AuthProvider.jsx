import { createContext, useState } from "react"

export const AuthContext = createContext()

const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null)

  async function signin(user, password) {
    // TODO: Petición a la api.
    try {
      // Simular una petición a la API (esperar 1 segundo)
      console.log(`Iniciando sesión para: \nUser: ${user} \nPassword: ${password}`)
      await new Promise(resolve => setTimeout(resolve, 1000))
      setUser(user)
      return { success: true }  
    } catch(error) {
      console.log("Error en inicio se sesión:", error)
      return { success: false, error: error.message}
    }
  }

  async function signout() {
    // TODO: Borrar token de inicio de sesión
    try {
      // Simular una operación de cierre de sesión (esperar 1 segundo)
      await new Promise(resolve => setTimeout(resolve, 1000));
      setUser(null);
      return { success: true };
    } catch (error) {
      console.error("Error en cierre de sesión:", error);
      return { success: false, error: error.message };
    }
  }

  const userSettings = {
    user,
    signin,
    signout 
  }

  return (
    <AuthContext.Provider value={userSettings}>
      {children}
    </AuthContext.Provider>
  )
}

export default AuthProvider