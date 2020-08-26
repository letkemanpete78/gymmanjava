import React, { Component } from 'react'
import uuid from 'react-uuid'
// import {User} from './DTO/User'
// import {Exercise} from './DTO/Exercise'
// import {Activity} from './DTO/Activity'
// import {Activity} from './DTO/Routine'

class App extends Component {

    // componentDidMount() {
        // const activity1 = {
        //     id:"activity1",
        //     uuid:uuid(),
        //     name:"test name",
        //     description:"test description",
        //     resourceFile:{fileId:1}
        //  }

        // const activity = Activity.createActivity(activity1)
        // console.log(activity)
    // }

    loginForm = () => {
        return (
        <div id="loginform">
            <p><label for="email">E-mail:</label> <input id="email" type="text" name="email" placeholder="email@domain.com"/></p>
            <p><label for="password">Password:</label> <input id="password" type="password" name="password"/></p>
            <p><button>Login</button></p>
            <p>Google</p>
        </div>)
    }

    routineList = (routines) => {
        return (
            <div id="routines">
                {
                    routines.forEach(routine => {
                        this.routineDetails(routine)    
                    })
                }
            </div>
        )
    }

    routineDetails = (routine) => {
        return (
            <div id={routine.uuid}>
                <p>{routine.name}</p>
                <p>{routine.description}</p>
                this.exerciseList(routine.exercises)
            </div>
        )
    }

    exerciseList = (exercises) => {
        return (
            <div id="exercises">
                {
                    exercises.forEach(exercise => {
                        this.exerciseDetails(exercise)
                    })
                }
            </div>
        )
    }

    exerciseDetails = (exercise) => {
        return (
            <div id={exercise.uuid}>
                <p>{exercise.name}</p>
                <p>{exercise.description}</p>
                <p>{exercise.type}</p>
                <p>{exercise.value}</p>
                <p>{exercise.unit}</p>
                this.activityDetails(exercise.activity)
            </div>
        )
    }

    activityDetails = (activity) => {
        return (
            <div id={activity.uuid}>
                <p>{activity.name}</p>
                <p>{activity.description}</p>
                this.resourceFileDetails(activity.resourceFile)
            </div>
        )
    }

    resourceFileDetails = (resourceFile) => {
        return (
            <div id={resourceFile.fielId}>
                <image>{resourceFile.fileName} - {resourceFile.desciprtion}</image>
            </div>
        )
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