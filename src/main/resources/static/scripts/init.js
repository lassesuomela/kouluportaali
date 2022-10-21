const url = "http://localhost:8080";

const getUsers = () => {
    axios.get(url + "/students")
    .then(response => {

        const studentTable = document.getElementById("studentTable");
        const studentList = document.getElementById("studentList");

        // loop through the students
        // create option and assign students id to option value
        // and assign students name to option text

        // also create row for every object in the student array
        // and then create new cells in the same row and 
        // set those cells values equal to the student id, name, dob and courses
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
                        
                        // check if there is a need to add ", " to the end of courses
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

        const courseTable = document.getElementById("courseTable");
        const courseList = document.getElementById("courseList");

        // loop through the courses array
        // and get the course code and course name
        // assign those to the option that is created and assign
        // options value to course code 

        // also create new row and cells and assing course data to those
        // and show it in the course table
        for (let i = 0; i < response.data.length; i++){

            let option = document.createElement("option");

            option.value = response.data[i]["code"];
            option.innerText = response.data[i]["name"];

            courseList.append(option);

            let newRow = courseTable.insertRow();

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
getCourses();
