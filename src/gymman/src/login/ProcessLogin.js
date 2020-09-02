export default (async function ProcessLogin(values) {
    window.alert(`You submitted:\n\n${JSON.stringify(values, null, 2)}`);
    
    try {
        if (process.env.REACT_APP_LOGIN_VERB !== "GET") {
            const response = await fetch(`${process.env.REACT_APP_LOGIN_URL}`, { method: `${process.env.REACT_APP_LOGIN_URL}`, body: JSON.stringify(values) })
            if (response.ok) {
                return await response.json()
            }
        } else {
            const response = await fetch(`${process.env.REACT_LOGIN_URL}`, {})
            if (response.ok) {
                return await response.json()
            }
        }
    } catch (err) {
        console.log(err)
    }
});