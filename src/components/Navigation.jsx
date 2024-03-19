import '../styles/components/Navigation.css'
import links from '../assets/paths.json'
import { NavLink } from 'react-router-dom'
import useAuth from './useAuth'

const Navigation = ({ children }) => {
  const auth = useAuth()

  const handleClass = ({ isActive, isPending, isTransitioning }) => {
    return (
      isActive ? "active" :
        isPending ? "pending" :
          isTransitioning ? "transitioning" : ""
    )
  }

  return (
    <nav className='navbar'>
      {children}
      <ul>
        {
          auth.user && Array.isArray(links) && links.filter(link => link.auth === 1).map((link, index) => (
            <li key={index}>
              <NavLink key={index} to={link.path} className={handleClass}>
                {link.text}
              </NavLink>
            </li>
          ))
        }
        {
          !auth.user && Array.isArray(links) && links.filter(link => link.auth === 0).map((link, index) => (
            <li key={index}>
              <NavLink key={index} to={link.path} className={handleClass}>
                {link.text}
              </NavLink>
            </li>
          ))
        }
      </ul>
    </nav>
  )
}

export default Navigation
