import React from 'react';
import { connect } from 'react-redux';
import { Field, reduxForm, formValueSelector } from 'redux-form';

let LoginForm = props => {
    const {
        email,
        password,
        handleSubmit,
        pristine,
        reset,
        submitting,
    } = props;
    return (
        <form onSubmit={handleSubmit}>

            <label>Email</label>
            <div>
                <Field
                    name="email"
                    component="input"
                    type="email"
                    placeholder="Email"
                    autoComplete="off"
                />
            </div>
            <label>Password</label>
            <div>
                <Field
                    name="password"
                    component="input"
                    type="password"
                    placeholder="password"
                    autoComplete="off"
                />
            </div>
            <div>
                <button type="button" disabled={pristine || submitting} onClick={handleSubmit}>
                    Login
                </button>
            </div>
        </form>
    );
};

// Decorate with redux-form
LoginForm = reduxForm({
    form: 'LoginForm', // a unique identifier for this form
})(LoginForm);

// Decorate with connect to read form values
const selector = formValueSelector('LoginForm'); // <-- same as form name
LoginForm = connect(state => {
    // can select values individually
    const email = selector(state, 'email');
    const password = selector(state, 'password');
    return {
        email,
        password
    };
})(LoginForm);

async function ProcessLogin(values) {
    window.alert(`You submitted:\n\n${JSON.stringify(values, null, 2)}`);
};

export default LoginForm;