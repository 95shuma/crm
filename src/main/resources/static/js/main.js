'use strict';
//--< ============================================================== Константы ====================================================================
const baseUrl = 'http://localhost:7777';
//--> ============================================================== Константы ====================================================================
//--< ============================================================== Переменные ====================================================================
let newLet;
//--> ============================================================== Переменные ====================================================================
//--< ============================================================== Объекты ======================================================================

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

//--> ============================================================== Запросы ========================================================================
//--< ============================================================== Обновляю ==============================================================

//--> ============================================================== Обновляю ==============================================================
