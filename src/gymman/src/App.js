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
                <p><label for="email">E-mail:</label> <input id="email" type="text" name="email" placeholder="email@domain.com" /></p>
                <p><label for="password">Password:</label> <input id="password" type="password" name="password" /></p>
                <p><button>Login</button></p>
                <p>Google</p>
            </div>)
    }

    routineList = (routines, isEditable) => {
        return (
            <div id="routines">
                {
                    routines.forEach(routine => {
                        this.routineDetails(routine, isEditable)
                    })
                }
            </div>
        )
    }

    routineDetails = (routine, isEditable) => {
        return (
            <div id={routine.uuid}>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{routine.name}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{routine.description}</p>
                this.exerciseList(routine.exercises)
            </div>
        )
    }

    exerciseList = (exercises, isEditable) => {
        return (
            <div id="exercises">
                {
                    exercises.forEach(exercise => {
                        this.exerciseDetails(exercise, isEditable)
                    })
                }
            </div>
        )
    }

    exerciseDetails = (exercise, isEditable) => {
        return (
            <div id={exercise.uuid}>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.name}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.description}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.type}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.value}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.unit}</p>
                this.activityDetails(exercise.activity,isEditable)
            </div>
        )
    }

    activityDetails = (activity, isEditable) => {
        return (
            <div id={activity.uuid}>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{activity.name}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{activity.description}</p>
                this.resourceFileDetails(activity.resourceFile,isEditable)
            </div>
        )
    }

    resourceFileDetails = (resourceFile, isEditable) => {
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