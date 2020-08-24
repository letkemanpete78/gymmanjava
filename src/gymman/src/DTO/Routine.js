/*
        const user1 = {
            id: "userid1",
            uuid: uuid(),
            fName: "ASSET",
            routines:[
                {id:"routineid1",uuid:"uuid1"},
                {id:"routineid2",uuid:"uuid2"},
            ]
        };



        const user = Routine.createRoutine(user1)
        console.log(user)
*/
export const Routine = {

    allRoutines : async function () {
        try{
            const response = await fetch(`${process.env.REACT_APP_ROUTINE_LIST_URL}`,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.debug(err)
        }
    },

    loadRoutine : async function (id) {
        try{
            const response = await fetch(`${process.env.REACT_APP_ROUTINE_SINGLE_URL}` + id,{})
            if (response.ok) {
                return await response.json()
            }
        } catch (err){
            console.log(err)
        }
    },

    deleteRoutine : async function (id) {
        try{
            if (process.env.REACT_APP_DELETE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_DELETE_URL}` ,{method: `${process.env.REACT_APP_DELETE_VERB}`,body: JSON.stringify(id)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_DELETE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    updateRoutine: async function (user) {
        try{
            if (process.env.REACT_APP_UPDATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_UPDATE_URL}` ,{method: `${process.env.REACT_APP_ROUTINE_UPDATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_UPDATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    },

    createRoutine: async function (user) {
        try{
            if (process.env.REACT_APP_CREATE_VERB !== "GET"){
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_CREATE_URL}` ,{method: `${process.env.REACT_APP_ROUTINE_CREATE_URL}`,body: JSON.stringify(user)})
                if (response.ok) {
                    return await response.json()
                }
            } else {
                const response = await fetch(`${process.env.REACT_APP_ROUTINE_CREATE_URL}` ,{})
                if (response.ok) {
                    return await response.json()
                }
            }
        } catch (err){
            console.log(err)
        }
    }

}