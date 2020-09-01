import React, { Component } from 'react'
import { Provider } from "react-redux";
import store from "./store";
import LoginForm from './LoginForm'
import ProcessLogin from './ProcessLogin'

class Login extends Component {

    render() {
        return (
            <Provider store={store}>
                <LoginForm onSubmit={ProcessLogin} />
            </Provider>
        )
    }
}
export default Login

// https://codesandbox.io/s/redux-form-selecting-form-values-example-forked-7z2dh?file=/index.js:61-100