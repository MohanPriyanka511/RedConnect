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

