const url = "http://localhost:8080";

const getUsers = () => {
    axios.get(url + "/students")
    .then(response => {

        const studentTable = document.getElementById("studentTable");

        for (let i = 0; i < response.data.length; i++){
            let newRow = studentTable.insertRow();

            for(const key in response.data[0]){

                let newCell = newRow.insertCell();
    
                let newText = document.createTextNode(response.data[i][key]);
                newCell.appendChild(newText);
            }
        }
    })
    .catch(error => console.error(error));
};

getUsers();
