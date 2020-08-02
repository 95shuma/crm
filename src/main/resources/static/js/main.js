'use strict';
//--< ============================================================== Константы ====================================================================
const baseUrl = 'http://localhost:7777';
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
function Hospital(id, name, Place, address) {
    this.id = id;
    this.name = name;
    this.Place = Place;
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
function User(id, inn, documentNumber, fullName, name, surname, middleName, birthDate, gender, Place) {
    this.id = id;
    this.inn = inn;
    this.documentNumber = documentNumber;
    this.fullName = fullName;
    this.name = name;
    this.surname = surname;
    this.middleName = middleName;
    this.birthDate = birthDate;
    this.gender = gender;
    this.Place = Place;
}
function RegistrationJournal(id, Hospital, User, Position, Role) {
    this.id = id;
    this.Hospital = Hospital;
    this.User = User;
    this.Position = Position;
    this.Role = Role;
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
const fetchDoctorsByPosition = async (idx) => {
    const getPath = `${baseUrl}/users/positions/${idx}`;
    const data = await fetch(getPath, {cache: 'no-cache'});
    return data.json();
};
function getDoctorsByPosition(fetch) {
    fetch.then(data => {
        //console.log(data);
        // for (let i = 0; i < data.content.length; i++) {
        //     let registrationJournal = new RegistrationJournal(data.content[i].id)
        // }
    });
}
//--> ============================================================== Запросы ========================================================================
//--< ============================================================== Обновляю ==============================================================
const selectElement = document.getElementById('positionIdList');
selectElement.addEventListener('change', (event) => {
    getDoctorsByPosition(fetchDoctorsByPosition(event.target.value));
});

//--> ============================================================== Обновляю ==============================================================
