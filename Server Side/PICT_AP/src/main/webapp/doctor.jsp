<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Doctor Login</title>
    <!-- <link rel="stylesheet" href="style.css"> -->
    <style>
        * {
            padding: 0;
            margin: 0;
            box-sizing: border-box;
        }
			
nav{
    background-color: aliceblue;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 15px 55px;
}
nav ul{
    display: flex;
    align-items: center;
    gap:15px;
    list-style: none;
}
nav ul li a{
    font-size: 21px;
    color: rgb(39, 2, 2);
    text-decoration: none;
    padding: 8px 4px;
    border-radius: 5px;
}

nav ul li a:hover{
    /* background-color: #6a6b6c5f; */
    box-shadow: 0 0 2px rgb(10, 11, 11);
}
        section:nth-of-type(1) {
            min-height: 350px;
            background-color: bisque;
            padding: 4px 0;
        }

        section:nth-of-type(1) p {
            text-align: center;
            font-size: 45px;
            margin: 15px 0;
        }

        section:nth-of-type(1) table {
            text-align: center;

            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        th,
        td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
            font-size: large;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* Status Colors */
        .waiting {
            background-color: #ffc107;
            color: black;
            font-weight: bold;
            padding: 4px;
            font-size: 19px;
        }

        .in-progress {
            background-color: green;
            color: white;
            font-weight: bold;
            padding: 4px;
            font-size: 19px;
        }

        .completed {
            background-color: #28a745;
            color: white;
            font-weight: bold;
        }

        #checked {
            display: block;
            margin: auto;
            font-size: 21px;
            padding: 5px 3px;
            border-radius: 5px;
        }

        section:nth-of-type(1) table th {
            font-size: 29px;
            font-weight: 500;
        }

        section:nth-of-type(1) table td {
            font-size: 21px;
            font-weight: 500;
        }

        section:nth-of-type(2) {
            min-height: 350px;
            background-color: aliceblue;
            padding: 40px 10px;
            display: flex;
            align-items: center;
            justify-content: space-between;
        }

        section:nth-of-type(2) div:nth-of-type(1) {
            /* border: 2px solid palegreen; */
            width: 30%;
        }

        section:nth-of-type(2) div:nth-of-type(2) {
            /* border: 2px solid palegreen; */
            width: 70%;
        }

        section:nth-of-type(2) div img {
            width: 300px;
            min-height: 300px;
            border: 1px solid tomato;
            border-radius: 15px 30px 0 0;
            display: block;
            margin: auto;
        }
        #current-patient{
            width: 100%;
            height: 100%;
        }
        #current-patient th:nth-of-type(1){
            font-size: 25px;
            background-color: #2a3691;
            width: 40%;
        }
        #current-patient th:nth-of-type(2){
            font-size: 25px;
            width: 60%;
            background-color: rgba(0, 0, 128, 0.586);
        }
        section:nth-of-type(3) {
            min-height: 150px;
            background-color: aliceblue;
            padding: 4px 0;
        }
    </style>
</head>

<body>
    <!-- section for OPD Queue Display -->
    <%
    	String uname = (String) request.getAttribute("name");
    %>
    <nav>
        <div>
            <p>
            	<a href="http://127.0.0.1:5500/index.html"  style="text-decoration: none; font-size: 25px; color: rgb(30, 0, 0); border: none;">AP_PICT</a>
            </p>
        </div>
        <h1>Welcome <%= uname %></h1>
        <div>
            <ul>
                <li><a href="http://127.0.0.1:5500/index.html">Home</a></li>
               </ul>
        </div>
    </nav>
    <!--<h1>Welcome <%= uname %></h1>-->
    <section id="#opd">
        <!-- OPD Queue Display -->
        <p>Live OPD Queue</p>
        <p>Patient in Queue: <span id="qc"></span></p>
        <table border="2" class="Showtable">
            <tr>
                <th>Token Number</th>
                <th>Patient Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Doctor Assigned</th>
                <th>Department</th>
                <th>Status</th>
            </tr>
        </table>
    </section>
    <section>
        <div>
            <img src="https://th.bing.com/th/id/OIP.9HSVWoQmEJzdOtuhxXWmOAHaHa?w=181&h=181&c=7&r=0&o=7&dpr=1.3&pid=1.7&rm=3" alt="patient-img">
        </div>
        <div>
            <table id="current-patient">
                <tr>
                    <th>Token Number</th>
                    <th id="token"></th>
                </tr>
                <tr>
                    <th>Patient Name</th>
                    <th id="patientname"></th>
                </tr>
                <tr>
                    <th>Age</th>
                    <th id="age"></th>
                </tr>
                <tr>
                    <th>Gender</th>
                    <th id="gender"></th>
                </tr>
                <tr>
                    <th>Doctor Assigned</th>
                    <th id="docassign"></th>
                </tr>
                <tr>
                    <th>Department</th>
                    <th id="dept"></th>
                </tr>
            </table>
        </div>
    </section>
    <section>
        <button style="cursor: pointer;" type="button" id="checked">Checked</button>
    </section>
    <script >
 // Insert OPD data into table
    fetch("http://localhost:8080/PICT_AP/SendOPDQueueData").then((e) => e.json().then((data) => {

        console.log(data)

        patient_data = Object.values(data);
        let i = 0;
        let c = 0;
        patient_data.forEach(e => {
            console.log(e)

            let row = document.createElement("tr");

            let srno = document.createElement("td");
            srno.textContent = e.token;
            row.appendChild(srno)

            let patientname = document.createElement("td")
            patientname.textContent = e.name
            row.appendChild(patientname)

            let age = document.createElement("td")
            age.textContent = e.age
            row.appendChild(age)

            let gender = document.createElement("td")
            gender.textContent = e.gender
            row.appendChild(gender)

            let doctor_assign = document.createElement("td")
            doctor_assign.textContent = e.doctor_assign
            row.appendChild(doctor_assign)

            let department = document.createElement("td")
            department.textContent = e.department
            row.appendChild(department)

            let status = document.createElement("td")
            let sbtn = document.createElement("button")
            sbtn.textContent = e.status
            sbtn.style.cursor = "pointer"

            if (e.status == "waiting") {
                sbtn.classList.add("waiting")
            }
            else if (e.status = "in-progress") {
                sbtn.classList.add("in-progress")
            }
            status.appendChild(sbtn)
            row.appendChild(status)

            // current-patient
            c += 1;
            document.querySelector(".Showtable").appendChild(row)

            sbtn.onclick = (e) => {
                // alert("PM")
                console.log(e.target, patientname)
                let cptable = document.querySelector("#current-patient");
                document.querySelector("#token").textContent = srno.textContent;
                document.querySelector("#patientname").textContent = patientname.textContent;

                document.querySelector("#age").textContent = age.textContent;
                document.querySelector("#gender").textContent = gender.textContent;
                document.querySelector("#docassign").textContent = doctor_assign.textContent;
                document.querySelector("#dept").textContent = department.textContent;
                fetch("http://localhost:8080/PICT_AP/ChangeStatus?token=" + document.querySelector("#token").textContent).then(e => console.log(e))
                // location.reload()
            }

            document.querySelector("#checked").onclick = () => {
                // alert("pm")
                // if()
                location.reload()
                fetch("http://localhost:8080/PICT_AP/PatientChecked?token=" + document.querySelector("#token").textContent).then(e => console.log(e))
            }


        })
        document.querySelector("#qc").textContent = c;
    }))


    </script>
</body>

</html>