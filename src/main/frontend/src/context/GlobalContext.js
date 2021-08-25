import React, { createContext, useReducer } from 'react';

const initalState = {
    isAuthenticated: localStorage.getItem("token") == null ? false : true,
    user: localStorage.getItem("user") == null ? null : localStorage.getItem("user"),
    token: localStorage.getItem("token") == null ? null : localStorage.getItem("token"),
    roles: localStorage.getItem("roles") == null ? null : localStorage.getItem("roles")
};

console.log(initalState);

export const GlobalContext = createContext(initalState);

const reducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            console.log(state);
            console.log(action);
            localStorage.setItem("user", action.payload.user);
            localStorage.setItem("token", action.payload.token);
            localStorage.setItem("roles", JSON.stringify(action.payload.roles));
            return {
                ...state,
                isAuthenticated: true,
                user: action.payload.user,
                token: action.payload.token,
                roles: action.payload.roles
            };
        case "LOGOUT":
            localStorage.clear();
            return {
                ...state,
                isAuthenticated: false,
                user: null,
                token: null
            };
        case "CHANGE_AUTHENTICATION":
            return {
                ...state,
                isAuthenticated: action.payload
            };
        default:
            return state;
    }
}

export const GlobalProvider = ({ children }) => {
    const [state, dispatch] = useReducer(reducer, initalState);

    return (
        <GlobalContext.Provider value={{ state, dispatch }} >
            {children}
        </GlobalContext.Provider>
    )
}