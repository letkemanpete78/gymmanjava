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
            <div>
                <div id="header">
                    <ul>
                        <li>Register/login</li>
                        <li>Last Sync</li>
                    </ul>
                </div>
                <div id="left-menu">
                    <ul>
                        <li>
                            Workout Routines
                            <ul>
                                <li>Your History</li>
                                <li>Gallery</li>
                                <li>Active</li>
                            </ul>
                        </li>
                        <li>Exercises</li>
                        <li>Activity</li>
                        <li>FAQ</li>
                        <li>About</li>
                        <li>Terms And Conditions</li>
                    </ul>
                </div>
                <div id="main-content">
                    <div id="user-stats">
                        <ul>
                            <li>Last Sync</li>
                            <li>User Details</li>
                        </ul>
                    </div>
                    <div id="quick-links">See left menu</div>
                </div>
            </div>
        )
    }
}


export default App