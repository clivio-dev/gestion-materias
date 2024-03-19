import useAuth from "../components/useAuth"
import { useNavigate } from "react-router-dom"

const Logout = () => {
  const auth = useAuth()
  const navigate = useNavigate()

  const handleLogout = async () => {
    const result = await auth.signout()
    
    if(result.success)
      navigate("/login")
  }

  handleLogout()

  return (
    <>
    </>
  )
}

export default Logout