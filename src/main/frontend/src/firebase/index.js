import firebase from 'firebase/compat/app';
import "firebase/compat/storage";

// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
    apiKey: "AIzaSyDXmBSBfLO2vtECEwDRtVjHURHgowgHuzw",
    authDomain: "sign-learning-platform.firebaseapp.com",
    projectId: "sign-learning-platform",
    storageBucket: "sign-learning-platform.appspot.com",
    messagingSenderId: "213185889733",
    appId: "1:213185889733:web:35e9407f4f30ec8faaf7ac",
    measurementId: "G-D2TYPN6WL2"
};

firebase.initializeApp(firebaseConfig);

const storage = firebase.storage();

export { storage, firebase as default };