import { createContext, useContext, useMemo, useState } from "react";

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [token, setToken] = useState(sessionStorage.getItem("token"));
  const [user, setUser] = useState({
    name: sessionStorage.getItem("name") || "",
    email: sessionStorage.getItem("email") || "",
  });

  const login = (data) => {
    sessionStorage.setItem("token", data.token);
    sessionStorage.setItem("name", data.name);
    sessionStorage.setItem("email", data.email);
    setToken(data.token);
    setUser({ name: data.name, email: data.email });
  };

  const logout = () => {
    sessionStorage.clear();
    setToken(null);
    setUser({ name: "", email: "" });
  };

  const value = useMemo(() => ({ token, user, login, logout }), [token, user]);
  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export const useAuth = () => useContext(AuthContext);
