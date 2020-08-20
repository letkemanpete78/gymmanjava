// import React from 'react';

// function Users() {

    // fetch(`${process.env.REACT_APP_BASE_URL}/users/`)
    //   .then(/* do your stuff here*/)
    //   .then(/* do your stuff here*/)
    //   .catch(/* handle errors*/);
    // 
    // loadShortLibilities(urlShortLiability) {
    //     fetch(urlShortLiability)
    //         .then(result => result.json())
    //         .then(result => {
    //             if ((result.length !== 0) && (typeof result[0].currency !== 'undefined')) {
    //                 this.setState({
    //                     selectedCurrency: result[0].currency,
    //                     currencyHistory : [result[0].currency]
    //                 })
    //             }
    //             this.setState({
    //                 liabilityDataShort: result,
    //             })
    //         })
    // }


//   return (
//     <div className="User">

//     </div>
//   );
// }

function loadUsers(url){
    console.log(`${process.env.REACT_APP_BASE_URL}` + url)
    fetch(`${process.env.REACT_APP_BASE_URL}` + url)
        .then(result => result.json())
        .then(result =>{
            if (result.length !== 0){
                console.log(result)
            }
        })
        .catch(/* handle errors*/);
    }
// export default Users;
