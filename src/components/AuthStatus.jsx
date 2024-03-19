import { useNavigate } from "react-router-dom"
import useAuth from "./useAuth"

const AuthStatus = () => {
  const auth = useAuth()
  const navigate = useNavigate()

  if (!auth.user) {
    return <p>No iniciaste sesión.</p>
  }

  return (
    <p>
      Bienvenido {auth.user}!{" "}
      <button
        onClick={() => {
          auth.signout(() => navigate("/login"))
        }}
      >
        Cerrar sesión
      </button>
    </p>
  )
}

export default AuthStatus