/*
        const exercise1 = {
            id:"exerciseid1",
            type:"TIMED",
            value:123,
            unit:"SECONDS",
            uuid:uuid(),
            name:"abc",
            description:"def",
            activity:{id:"activityid2", uuid:"test uuid"}
        }

        const exercise = Exercise.createExercise(exercise1)
        console.log(exercise)
*/
export const Exercise = {

    allExercises : async function () {
        try{
            const response = await fetch(`${process.env.REACT_APP_EXERCISE_LIST_URL}`,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.debug(err)
        }
    },

    loadExercise : async function (id) {
        try{
            const response = await fetch(`${process.env.REACT_APP_EXERCISE_SINGLE_URL}` + id,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.log(err)
        }
    },

    deleteExercise : async function (id) {
        try{
            if (process.env.REACT_APP_DELETE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_DELETE_URL}` ,{method: `${process.env.REACT_APP_DELETE_VERB}`,body: JSON.stringify(id)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_DELETE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    updateExercise: async function (user) {
        try{
            if (process.env.REACT_APP_UPDATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_UPDATE_URL}` ,{method: `${process.env.REACT_APP_EXERCISE_UPDATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_UPDATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    createExercise: async function (user) {
        try{
            if (process.env.REACT_APP_CREATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_CREATE_URL}` ,{method: `${process.env.REACT_APP_EXERCISE_CREATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_EXERCISE_CREATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    exerciseList : (exercises, isEditable) => {
        return (
            <div id="exercises">
                {
                    exercises.forEach(exercise => {
                        this.exerciseDetails(exercise, isEditable)
                    })
                }
            </div>
        )
    },

    exerciseDetails : (exercise, isEditable) => {
        return (
            <div id={exercise.uuid}>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.name}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.description}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.type}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.value}</p>
                <p contentEditable={isEditable} suppressContentEditableWarning={true}>{exercise.unit}</p>
                Activity.activityDetails(exercise.activity,isEditable)
                Utility.submitButtons(isEditable, exercise.uuid, saveMethod, updateMethod, deleteMethod)
            </div>
        )
    }
}