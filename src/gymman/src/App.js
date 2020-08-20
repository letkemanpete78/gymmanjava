import React, { Component } from 'react'

class App extends Component {

    loadUsers(){
        console.log(`${process.env.REACT_APP_BASE_USER_LIST_URL}`)
        fetch(`${process.env.REACT_APP_BASE_USER_LIST_URL}`)
        
            .then(result => result.json())
            .then(result =>{
                if (result.length !== 0){
                    console.log(result)
                }
            })
            .catch(/* handle errors*/);
        }

    componentDidMount() {

        // const urlShortAsset = 'http://localhost:8080/?category=short_term&type=asset'

        // this.loadLongLibilities(urlLongLiability)   
        this.loadUsers("/users/v1/list");
    }

    render() {
        return (
            <p>Pete</p>
        )


    }
}

export default App