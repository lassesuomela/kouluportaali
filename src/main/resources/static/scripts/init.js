const url = "http://localhost:8080";

const getUsers = () => {
    axios.get(url + "/students")
    .then(response => {

        const studentTable = document.getElementById("studentTable");
        const studentList = document.getElementById("studentList");

        for (let i = 0; i < response.data.length; i++){

            let option = document.createElement("option");

            option.value = response.data[i]["id"];
            option.innerText = response.data[i]["firstName"] + " " + response.data[i]["lastName"];

            studentList.append(option);

            let newRow = studentTable.insertRow();

            for(const key in response.data[0]){

                let newCell = newRow.insertCell();
    
                let newText;
                if(key !== "courses"){
                    newText = document.createTextNode(response.data[i][key]);
                }else{

                    let courseNames = "";
                    for(let j = 0; j < response.data[i][key].length; j++){
                        const courseData = response.data[i][key][j];

                        courseNames += courseData["name"]
                        
                        response.data[i][key].length > 1 && j !== response.data[i][key].length -1 ? courseNames += ", " : ""
                    }

                    newText = document.createTextNode(courseNames);
                }
                newCell.appendChild(newText);
            }
        }


    })
    .catch(error => console.error(error));
};

const getCourses = () => {
    axios.get(url + "/courses")
    .then(response => {

        const courseList = document.getElementById("courseList");

        for (let i = 0; i < response.data.length; i++){

            let option = document.createElement("option");

            option.value = response.data[i]["code"];
            option.innerText = response.data[i]["name"];

            courseList.append(option);
        }
    })
    .catch(error => console.error(error));
};

getUsers();
getCourses();
