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
function createPositionsSelect() {
    let positionsSelect = document.createElement('div');
    positionsSelect.innerHTML =
        `<h5 class="wt-3">1) Выберите специальность:</h5>
        <select class="form-control" id="positionsSelect" name="positionIdList">
            <option disabled hidden selected value> -- выберите -- </option>
                
        </select>`
    ;
    return positionsSelect;
}
function createPositionsSelectOption(position) {
    let positionsSelectOption = document.createElement('option');
    positionsSelectOption.value = position.id;
    positionsSelectOption.innerText = position.name;
    return positionsSelectOption;
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
const fetchPositionsByHospital = async (hospitalId) => {
    const getPath = `${baseUrl}/positions/hospital/${hospitalId}`;
    const data = await fetch(getPath, {cache: 'no-cache'});
    return data.json();
};
function drowPositionsSelect(fetch) {
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
        }
    });

}
//--> ============================================================== Запросы ========================================================================
//--< ============================================================== Обновляю ==============================================================
const selectElement = document.getElementById('hospitalSelect');
selectElement.addEventListener('change', (event) => {
    drowPositionsSelect(fetchPositionsByHospital(event.target.value));
});

//--> ============================================================== Обновляю ==============================================================
