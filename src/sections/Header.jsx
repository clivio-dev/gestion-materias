import Navigation from "../components/Navigation"
import Logo from '../components/Logo'

const Header = () => {
  return (
    <>
      <Navigation>
        <Logo width={40} height={40} color={"var(--primary-color)"}/>
      </Navigation>
    </>
  )
}

export default Header