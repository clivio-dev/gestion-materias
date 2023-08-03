import Link from './Link.js';

function Nav() {
  return(
    <div className="navbar">
      <Link href="/inicio" className="item">
        Inicio
      </Link>
      <Link href="/materias" className="item">
        Materias
      </Link>
      <Link href="/carreras" className="item">
        Carreras
      </Link>
    </div>
  );
}

export default Nav;
