import React, { createContext, useReducer } from 'react';
import { toast } from 'react-toastify';

const initalState = {
    isAuthenticated: localStorage.getItem("token") == null ? false : true,
    user: localStorage.getItem("user") == null ? null : localStorage.getItem("user"),
    token: localStorage.getItem("token") == null ? null : localStorage.getItem("token"),
    roles: localStorage.getItem("roles") == null ? null : localStorage.getItem("roles"),
    currentPage: "",
    globalNotification: null
};

console.log(initalState);

export const GlobalContext = createContext(initalState);

const reducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            localStorage.setItem("user", action.payload.user);
            localStorage.setItem("token", action.payload.token);
            localStorage.setItem("roles", JSON.stringify(action.payload.roles));
            toast.success("Zalogowano pomyślnie!", {
                position: "bottom-right"
            })
            return {
                ...state,
                isAuthenticated: true,
                user: action.payload.user,
                token: action.payload.token,
                roles: action.payload.roles
            };
        case "LOGOUT":
            localStorage.clear();
            toast.success("Wylogowano pomyślnie!", {
                position: "bottom-right"
            })
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
        case "SET_PAGE":
            console.log(action.payload.page);
            return {
                ...state,
                currentPage: action.payload.page
            }
        case "SET_NOTIFICATION":
            console.log(action.payload.globalNotification);
            return {
                ...state,
                globalNotification: action.payload.globalNotification
            }
        case "CLEAR_NOTIFICATION":
            console.log("Clear notification");
            return {
                ...state,
                globalNotification: null
            }
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