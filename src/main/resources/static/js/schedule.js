'use strict';
//--< ============================================================== Константы ====================================================================
const baseUrl = 'http://localhost:7777';
const positionsFromHospitalSelect = document.getElementById('positionsFromHospitalSelect');
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
function createPositionsSelect() {
    let positionsSelect = document.createElement('div');
    positionsSelect.innerHTML =
        `<h5 class="wt-3">2) Выберите специальность:</h5>
        <select class="form-control" id="positionsSelect" name="positionIdList">
            <option disabled hidden selected value> -- выберите -- </option>
        </select>
        <div id="regUsersSelectBlock"></div>
    `
    ;
    return positionsSelect;
}
function createPositionsSelectOption(position) {
    let positionsSelectOption = document.createElement('option');
    positionsSelectOption.value = position.id;
    positionsSelectOption.innerText = position.name;
    return positionsSelectOption;
}
function createRegUsersSelect() {
    let regUsersSelect = document.createElement('div');
    regUsersSelect.innerHTML =
        `<h5 class="wt-3">3) Выберите врача:</h5>
        <select class="form-control" id="regUsersSelect"">
            <option disabled hidden selected value> -- выберите -- </option>
        </select>
        <div id="scheduleBlock"></div>`
    ;
    return regUsersSelect;
}
function createRegUsersSelectOption(regUser) {
    let regUsersSelectOption = document.createElement('option');
    regUsersSelectOption.value = regUser.id;
    regUsersSelectOption.innerText = regUser.user.fullName;
    return regUsersSelectOption;
}
function addElement(dom_element, adding_element){
    dom_element.append(adding_element);
}
//--> ============================================================== Разные функции =================================================================
//--< ============================================================== Запросы ========================================================================
const fetchPositionsByHospital = async (hospitalId) => {
    const getPath = `${baseUrl}/positions/hospital/${hospitalId}`;
    const data = await fetch(getPath, {cache: 'no-cache'});
    return data.json();
};
const fetchRegUsersByPosition = async (positionId, hospitalId) => {
    const getPath = `${baseUrl}/users/position-and-hospital/${positionId}&${hospitalId}`;
    const data = await fetch(getPath, {cache: 'no-cache'});
    return data.json();
};
function drowRegUsersSelect(fetch) {
    fetch.then(data => {
        console.log(data);
        let regUsersSelectElement = document.getElementById('regUsersSelectBlock');
        regUsersSelectElement.innerHTML = '';
        if (data.length === 0){
            let regUsersSelectElementError = document.createElement('div');
            regUsersSelectElementError.className = 'alert alert-warning mt-1';
            regUsersSelectElementError.role = 'alert';
            regUsersSelectElementError.innerHTML = 'По выбранной специальности врачи не найдены';
            addElement(regUsersSelectElement, regUsersSelectElementError);
        } else {
            addElement(regUsersSelectElement, createRegUsersSelect());
            for (let i = 0; i < data.length; i++) {
                addElement(document.getElementById('regUsersSelect'), createRegUsersSelectOption(
                    new RegistrationJournal(data[i].id,
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
                    )
                ));
            }
        }
    });
}
function drowPositionsSelect(fetch, hospitalId) {
    fetch.then(data => {
        console.log(data);
        positionsFromHospitalSelect.innerHTML = '';
        if (data.length === 0){
            let positionsFromHospitalSelectError = document.createElement('div');
            positionsFromHospitalSelectError.className = 'alert alert-warning mt-1';
            positionsFromHospitalSelectError.role = 'alert';
            positionsFromHospitalSelectError.innerHTML = 'По выбранному ЛПУ специальности не найдены';
            addElement(positionsFromHospitalSelect, positionsFromHospitalSelectError);
        } else {
            addElement(positionsFromHospitalSelect, createPositionsSelect());
            for (let i = 0; i < data.length; i++) {
                addElement(document.getElementById('positionsSelect'), createPositionsSelectOption(
                    new Position(data[i].id, data[i].name)
                ));
            }
            document.getElementById('positionsSelect').addEventListener('change', (event) => {
                drowRegUsersSelect(fetchRegUsersByPosition(event.target.value, hospitalId));
            });
        }
    });
}
//--> ============================================================== Запросы ========================================================================
//--< ============================================================== Обновляю ==============================================================
const selectElement = document.getElementById('hospitalSelect');
selectElement.addEventListener('change', (event) => {
    drowPositionsSelect(fetchPositionsByHospital(event.target.value), event.target.value);
});

//--> ============================================================== Обновляю ==============================================================
