import React, { Component } from 'react'

class App extends Component {

    componentDidMount() {

        const user = deleteSingleUser(1)
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
        const response = await fetch(`${process.env.REACT_APP_USER_DELETE_URL}` + id,{method: `${process.env.REACT_APP_DELETE_VERB}`,body: JSON.stringify(id)})
        if (response.ok) {
            return await response.json()
        }
    } catch (err){
        console.log(err)
    }
}

export default App