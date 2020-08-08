'use strict';
//--< ============================================================== Константы ====================================================================
const baseUrl = 'http://localhost:7777';
const newSchedule = document.getElementById('newSchedule');
const _csrf = document.getElementById('_csrf').content;
const _csrf_token = document.getElementById('_csrf_token').content;
//--> ============================================================== Константы ====================================================================
//--< ============================================================== Переменные ====================================================================
let newLet;
//--> ============================================================== Переменные ====================================================================
//--< ============================================================== Объекты ======================================================================
function Place(id, name, codePlace, groupCode) {
    this.id = id;
    this.name = name;
    this.codePlace = codePlace;
    this.groupCode = groupCode;
}
function Hospital(id, name, place, address) {
    this.id = id;
    this.name = name;
    this.place = place;
    this.address = address;
}
function Position(id, name) {
    this.id = id;
    this.name = name;
}
function Role(id, name) {
    this.id = id;
    this.name = name;
}
function User(id, inn, documentNumber, fullName, name, surname, middleName, birthDate, gender, place) {
    this.id = id;
    this.inn = inn;
    this.documentNumber = documentNumber;
    this.fullName = fullName;
    this.name = name;
    this.surname = surname;
    this.middleName = middleName;
    this.birthDate = birthDate;
    this.gender = gender;
    this.place = place;
}
function RegistrationJournal(id, hospital, user, position, role) {
    this.id = id;
    this.hospital = hospital;
    this.user = user;
    this.position = position;
    this.role = role;
}

//--> ============================================================== Объекты ========================================================================
//--< ============================================================== Разные функции =================================================================
function saveUser(user) {
    const userAsJSON = JSON.stringify(user);
    localStorage.setItem('user', userAsJSON);
}
function restoreUser() {
    const userAsJSON = localStorage.getItem('user');
    return JSON.parse(userAsJSON);
}
function clearLocalStorage(){
    localStorage.clear();
    //при выходе обновить
}
function createScheduleForm() {
    let scheduleForm = document.createElement('form');
    scheduleForm.action = '/senior-doctor/schedules/new-schedule';
    scheduleForm.method = 'post';
    scheduleForm.style.marginBottom = '50px';
    scheduleForm.innerHTML =
        `<input type="hidden" name="${_csrf}" value="${_csrf_token}"/>
         <div id="regUserList">
            <div class="d-flex justify-content-center">
                <div class="mx-auto mt-3 w-100">
                    <h5>2) Выберите врачей:</h5>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Выбрать</th>
                            <th scope="col">ФИО</th>
                            <th scope="col">Должность</th>
                            <th scope="col">ИНН</th>
                        </tr>
                        </thead>
                        <tbody id="chosenRegUsersTableBody"></tbody>
                    </table>
                </div>
            </div>
        </div>
        <div>
            <h5>3) Настройте рабочую неделю:</h5>
            <div class="d-flex justify-content-center">
                <div class="mx-auto mt-3 w-100">
                    <table class="table table-striped">
                        <thead>
                            <tr style="text-align: center">
                                <th scope="col"> </th>
                                <th scope="col">ПН</th>
                                <th scope="col">ВТ</th>
                                <th scope="col">СР</th>
                                <th scope="col">ЧТ</th>
                                <th scope="col">ПТ</th>
                                <th scope="col">СБ</th>
                                <th scope="col">ВС</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>С</td>
                                <td><input type="time" name="monday_from" class="form-control"></td>
                                <td><input type="time" name="tuesday_from" class="form-control"></td>
                                <td><input type="time" name="wednesday_from" class="form-control"></td>
                                <td><input type="time" name="tuesday_from" class="form-control"></td>
                                <td><input type="time" name="friday_from" class="form-control"></td>
                                <td><input type="time" name="saturday_from" class="form-control"></td>
                                <td><input type="time" name="sunday_from" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>По</td>
                                <td><input type="time" name="monday_to" class="form-control"></td>
                                <td><input type="time" name="tuesday_to" class="form-control"></td>
                                <td><input type="time" name="wednesday_to" class="form-control"></td>
                                <td><input type="time" name="tuesday_to" class="form-control"></td>
                                <td><input type="time" name="friday_to" class="form-control"></td>
                                <td><input type="time" name="saturday_to" class="form-control"></td>
                                <td><input type="time" name="sunday_to" class="form-control"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            
        </div>
        <button id="newScheduleBtn" class="btn btn-primary btn-block" style="margin-top: 20px">Сформировать</button>`
    ;
    return scheduleForm;
}
function createChosenRegUserTr(regUser) {
    let chosenRegUserTr = document.createElement('tr');
    chosenRegUserTr.innerHTML = `
        <td style="text-align: center">
            <div class="form-check">
                <input class="form-check-input chosenRegUser" type="checkbox" name="chosenRegUser" id="chosenRegUser${regUser.user.id}" value="${regUser.user.id}">
            </div>
        </td>
        <td>${regUser.user.fullName}</td>
        <td>${regUser.position.name}</td>
        <td>${regUser.user.inn}</td>
    `;
    return chosenRegUserTr;
}

function addElement(dom_element, adding_element){
    dom_element.append(adding_element);
}
//Проверка, если в хранилище есть данные, то ввести их, иначе....
function checkLocalStorageForUserData() {
    if (localStorage.getItem('user') == null){

    } else {

    }
}
//--> ============================================================== Разные функции =================================================================
//--< ============================================================== Запросы ========================================================================
const fetchDoctorsByPosition = async (positionId) => {
    const getPath = `${baseUrl}/users/positions/${positionId}`;
    const data = await fetch(getPath, {cache: 'no-cache'});
    return data.json();
};
function getDoctorsByPosition(fetch) {
    fetch.then(data => {
        console.log(data);
        newSchedule.innerHTML = '';
        if (data.length === 0){
            let newScheduleError = document.createElement('div');
            newScheduleError.className = 'alert alert-warning mt-1';
            newScheduleError.role = 'alert';
            newScheduleError.innerHTML = 'По выбранной должности врачи не найдены';
            addElement(newSchedule, newScheduleError);
        } else {
            addElement(newSchedule , createScheduleForm());
            for (let i = 0; i<data.length; i++){
                addElement(document.getElementById('chosenRegUsersTableBody'), createChosenRegUserTr(new RegistrationJournal(data[i].id,
                    new Hospital(data[i].hospital.id, data[i].hospital.name, new Place(data[i].hospital.place.id, data[i].hospital.place.name, data[i].hospital.place.codePlace, data[i].hospital.place.groupCode ), data[i].hospital.address),
                    new User(   data[i].user.id,
                        data[i].user.inn,
                        data[i].user.documentNumber,
                        data[i].user.fullName,
                        data[i].user.name,
                        data[i].user.surname,
                        data[i].user.middleName,
                        data[i].user.birthDate,
                        data[i].user.gender,
                        new Place(data[i].user.place. id,data[i].user.place.name)
                    ),
                    new Position(data[i].position.id, data[i].position.name),
                    new Role(data[i].role.id, data[i].role.name)
                    ))
                );
            }
        }
    });
}
//--> ============================================================== Запросы ========================================================================
//--< ============================================================== Обновляю ==============================================================
const selectElement = document.getElementById('positionIdList');
selectElement.addEventListener('change', (event) => {
    getDoctorsByPosition(fetchDoctorsByPosition(event.target.value));
});

//--> ============================================================== Обновляю ==============================================================
