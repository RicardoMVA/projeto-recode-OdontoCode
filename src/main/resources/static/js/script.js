const index = document.querySelector("#index");

if (index !== null) {
  fetch(`/api/v1/patients`)
    .then((response) => {
      return response.json();
    })
    .then((dbResponse) => {
      const resultsDl = document.querySelector("#results");

      dbResponse.forEach((result) => {
        const resultName = document.createElement("dt");
        const resultId = document.createElement("dd");
        const resultCpf = document.createElement("dd");
        const resultBirth = document.createElement("dd");
        const resultGender = document.createElement("dd");
        const resultAppointments = document.createElement("dd");
        
        resultName.innerHTML = "Nome: " + result.name;
        resultId.innerHTML = "Id: " + result.id;
        resultCpf.innerHTML = "Cpf: " + result.cpf;
        resultBirth.innerHTML = "Aniversário: " + result.birthday.slice(0,10);
        resultGender.innerHTML = "Sexo: " + result.gender;

        if (result.appointments.length > 0) {
          const appointDl = document.createElement("dl");
          result.appointments.forEach((appointment) => {
            const appointHead = document.createElement("dt");
            const appointId = document.createElement("dd");
            const appointSpecialty = document.createElement("dd");
            const appointDate = document.createElement("dd");
            const appointValue = document.createElement("dd");
            const appointRemarked = document.createElement("dd");

            appointHead.innerHTML = "Consulta marcada:";
            appointId.innerHTML = "Id: " + appointment.id;
            appointSpecialty.innerHTML = "Especialidade: " + appointment.specialty;
            appointDate.innerHTML = "Data: " + appointment.date.slice(0,10);
            appointValue.innerHTML = "Valor: " + appointment.value;
            appointRemarked.innerHTML = "Remarcada: " + appointment.remarked;

            appointHead.appendChild(appointId);
            appointHead.appendChild(appointSpecialty);
            appointHead.appendChild(appointDate);
            appointHead.appendChild(appointValue);
            appointHead.appendChild(appointRemarked);
            appointDl.appendChild(appointHead);
          })

          resultAppointments.appendChild(appointDl);
        } else {
          resultAppointments.innerHTML = "Nenhuma consulta marcada.";
        }
        
        resultsDl.appendChild(resultName);
        resultsDl.appendChild(resultId);
        resultsDl.appendChild(resultCpf);
        resultsDl.appendChild(resultBirth);
        resultsDl.appendChild(resultGender);
        resultsDl.appendChild(resultAppointments);
        resultsDl.appendChild(document.createElement("br"));
      })

      // document.querySelector("#index").innerHTML = JSON.stringify(dbResponse); 
    })
    .catch((err) => {
      console.log(err);
    });
}
