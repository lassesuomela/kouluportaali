const url = "http://localhost:8080"

const getUsers = () => {
    axios.get(url + "/students")
    .then(response => {
        console.log(response.data);
    })
    .catch(error => console.error(error));
};

getUsers();
   