import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './routes/App'
import Subjects from './routes/Subjects'
import Login from './routes/Login'
import {
  createBrowserRouter,
  RouterProvider
} from 'react-router-dom'
import './index.css'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />
  },
  {
    path: "/subjects",
    element: <Subjects />
  },
  {
    path: "/login",
    element: <Login />
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>,
)
