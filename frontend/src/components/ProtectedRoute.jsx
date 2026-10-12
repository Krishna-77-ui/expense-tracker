import { Navigate, Outlet } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
export default function ProtectedRoute(){const {user}=useAuth();return user&&localStorage.getItem('expense_token')?<Outlet/>:<Navigate to="/login" replace/>;}
