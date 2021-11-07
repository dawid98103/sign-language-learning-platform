import React, { createContext, useReducer } from 'react';
import jwt_decode from 'jwt-decode';
import { toast } from 'react-toastify';

const getUserIdClaimFromToken = () => {
    const token = localStorage.getItem("token") == null ? null : localStorage.getItem("token")
    const claims = jwt_decode(token)
    return claims.userId;
}

const initalState = {
    isAuthenticated: localStorage.getItem("token") == null ? false : true,
    user: localStorage.getItem("user") == null ? null : localStorage.getItem("user"),
    avatarUrl: localStorage.getItem("avatarUrl") == null ? null : localStorage.getItem("avatarUrl"),
    token: localStorage.getItem("token") == null ? null : localStorage.getItem("token"),
    roles: localStorage.getItem("roles") == null ? null : localStorage.getItem("roles"),
    userId: localStorage.getItem("token") == null ? null : getUserIdClaimFromToken(),
    currentPage: "",
    globalNotification: null
};

console.log(initalState);

export const GlobalContext = createContext(initalState);

const reducer = (state, action) => {
    switch (action.type) {
        case "LOGIN":
            localStorage.setItem("user", action.payload.user);
            localStorage.setItem("avatarUrl", action.payload.userAvatar);
            localStorage.setItem("token", action.payload.token);
            localStorage.setItem("roles", JSON.stringify(action.payload.roles));
            toast.success("Zalogowano pomyślnie!", {
                position: "bottom-right"
            })
            return {
                ...state,
                isAuthenticated: true,
                user: action.payload.user,
                avatarUrl: action.payload.userAvatar,
                token: action.payload.token,
                roles: action.payload.roles,
                userId: getUserIdClaimFromToken()
            };

        case "REGISTER":
            toast.success("Zarejestrowano pomyślnie!, Możesz się zalogować!", {
                position: "bottom-right"
            })
            break;
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