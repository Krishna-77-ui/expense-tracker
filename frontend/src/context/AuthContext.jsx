import { createContext, useContext, useMemo, useState } from 'react';
import { login as loginRequest, register as registerRequest } from '../services/authService';
const AuthContext = createContext(null);
export function AuthProvider({ children }) {
 const [user, setUser] = useState(() => { try { return JSON.parse(localStorage.getItem('expense_user') || 'null'); } catch { return null; } });
 const save = (data) => { localStorage.setItem('expense_token', data.token); const next = { id: data.userId, name: data.name, email: data.email }; localStorage.setItem('expense_user', JSON.stringify(next)); setUser(next); };
 const signIn = async (data) => save(await loginRequest(data));
 const signUp = async (data) => save(await registerRequest(data));
 const signOut = () => { localStorage.removeItem('expense_token'); localStorage.removeItem('expense_user'); setUser(null); };
 const value = useMemo(() => ({ user, signIn, signUp, signOut }), [user]);
 return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}
export const useAuth = () => useContext(AuthContext);
