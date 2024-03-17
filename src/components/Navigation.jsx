import '../styles/components/Navigation.css'
import links from '../assets/paths.json'
import { NavLink } from 'react-router-dom'

const Navigation = ({ children }) => {
  const handleClass = ({ isActive, isPending, isTransitioning }) => {
    return(
      isActive ? "active" :
        isPending ? "pending" : 
          isTransitioning ? "transitioning" : ""
    )
  }

  return (
    <nav className='navbar'>
      {children}
      {Array.isArray(links) && links.map((link, index) => (
        <NavLink key={index} to={link.path} className={handleClass}>
          {link.text}
        </NavLink>
      ))}
    </nav>
  )
}

export default Navigation
