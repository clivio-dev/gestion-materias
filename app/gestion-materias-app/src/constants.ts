import { Page, PageBlock } from "./components/types";

export const DOMAIN_URL = new URL("http://localhost:5173");

export const LOGIN: Page = {
  url: new URL("login/", DOMAIN_URL),
  content: "Iniciar Sesión"
}

export const REGISTER: Page = {
  url: new URL("register/", DOMAIN_URL),
  content: "Registro"
}

export const HOME: PageBlock = {
  id: "#home",
  content: "Inicio"
} 

export const CONTACT: PageBlock = {
  id: "#contact",
  content: "Contacto"
}

export const SOCIAL_FACEBOOK = {
  url: new URL("https://facebook.com"),
  icon: "fa-brands fa-facebook"
}