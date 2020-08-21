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



        const user = createSingleUser(user1)
        console.log(user)
    }

    render() {
        return (
            <p>Pete</p>
        )
    }
}

async function loadAllUsers() {
    try{
        const response = await fetch(`${process.env.REACT_APP_USER_LIST_URL}`,{})
        if (response.ok) {
            return await response.json()
        }
    } catch (err){
        console.debug(err)
    }
}

async function loadSingleUser(id) {
    try{
        const response = await fetch(`${process.env.REACT_APP_USER_SINGLE_URL}` + id,{})
        if (response.ok) {
            return await response.json()
        }
    } catch (err){
        console.log(err)
    }
}

async function deleteSingleUser(id) {
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
}

async function updateSingleUser(user) {
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
}

async function createSingleUser(user) {
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

export default App