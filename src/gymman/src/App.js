import React, { Component } from 'react'
import uuid from 'react-uuid'

class App extends Component {

    componentDidMount() {
        const user1 = {
            id: "userid1",
            uuid: uuid(),
            fName: "ASSET",
            routines:[
                {id:"routineid1",uuid:"uuid1"},
                {id:"routineid2",uuid:"uuid2"},
            ]
        };



        const user = Users.createUser(user1)
        console.log(user)
    }

    render() {
        return (
            <p>Pete</p>
        )
    }
}

const Users = {
    logoutUser: async function(username,password) {
        const login = {Username:username,Password:password}
        try{
            if (process.env.REACT_APP_LOGOUT_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_USER_LOGOUT_URL}` ,{method: `${process.env.REACT_APP_LOGOUT_VERB}`,body: JSON.stringify(login)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_USER_LOGOUT_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    loginUser : async function (username,password) {
        const login = {Username:username,Password:password}
        try{
            if (process.env.REACT_APP_LOGIN_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_USER_LOGIN_URL}` ,{method: `${process.env.REACT_APP_LOGIN_VERB}`,body: JSON.stringify(login)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_USER_LOGIN_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    allUsers : async function () {
        try{
            const response = await fetch(`${process.env.REACT_APP_USER_LIST_URL}`,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.debug(err)
        }
    },

    loadUser : async function (id) {
        try{
            const response = await fetch(`${process.env.REACT_APP_USER_SINGLE_URL}` + id,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.log(err)
        }
    },

    deleteUser : async function (id) {
        try{
            if (process.env.REACT_APP_DELETE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_USER_DELETE_URL}` ,{method: `${process.env.REACT_APP_DELETE_VERB}`,body: JSON.stringify(id)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_USER_DELETE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    updateUser: async function (user) {
        try{
            if (process.env.REACT_APP_UPDATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_USER_UPDATE_URL}` ,{method: `${process.env.REACT_APP_USER_UPDATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_USER_UPDATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    createUser: async function (user) {
        try{
            if (process.env.REACT_APP_CREATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_USER_CREATE_URL}` ,{method: `${process.env.REACT_APP_USER_CREATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_USER_CREATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    }

}
export default App