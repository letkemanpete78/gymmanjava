import React, { Component } from 'react'
import uuid from 'react-uuid'
// import {User} from './DTO/User'
// import {Exercise} from './DTO/Exercise'
import {Activity} from './DTO/Activity'

class App extends Component {

    componentDidMount() {
        const activity1 = {
            id:"activity1",
            uuid:uuid(),
            name:"test name",
            description:"test description",
            resourceFile:{fileId:1}
         }

        const activity = Activity.createActivity(activity1)
        console.log(activity)
    }

    render() {
        return (
            <p>Pete</p>
        )
    }
}


export default App