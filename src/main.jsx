import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './routes/App'
import Subjects from './routes/Subjects'
import Login from './routes/Login'
import Logout from './routes/Logout'
import {
  createBrowserRouter,
  RouterProvider
} from 'react-router-dom'
import AuthProvider from './components/AuthProvider'
import RequireAuth from './components/RequireAuth'
import './index.css'

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />
  },
  {
    path: "/subjects",
    element: <RequireAuth><Subjects /></RequireAuth>
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/join",
    element: <Login />
  },
  {
    path: "logout",
    element: <RequireAuth><Logout /></RequireAuth>
  }
])

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <AuthProvider>
      <RouterProvider router={router} />
    </AuthProvider>
  </React.StrictMode>,
)
