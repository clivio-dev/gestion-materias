import React from 'react'
import ReactDOM from 'react-dom/client'
import {
  createBrowserRouter,
  RouterProvider
} from "react-router-dom"
import ErrorPage from './error-page'
import App from './App.tsx'
import './dist/output.css'
import Login from './routes/login'
import Register from './routes/register.tsx'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    errorElement: <ErrorPage />
  },
  {
    path: "login/",
    element: <Login />
  },
  {
    path: "register/",
    element: <Register />
  }
]);


ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
