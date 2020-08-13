<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <h3 class="mt-3">Результаты исследований</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Лаб. исследования</th>
            <th scope="col">Результат Лаб. исследования</th>
            <th scope="col">Инструм. исследования</th>
            <th scope="col">Результат Инструм. исследования</th>
            <th scope="col">Общее Состояние</th>
        </tr>
        </thead>
        <tbody id="Table">
        <#list items as examinationResult>
            <tr>
                <th scope="row">${examinationResult?counter}</th>
                <td>${examinationResult.labExaminationDTO.name}</td>
                <td>${examinationResult.labExaminationResult}</td>
                <td>${examinationResult.instrumExaminationDTO.name}</td>
                <td>${examinationResult.instrumExaminationResult}</td>
                <td>${examinationResult.generalState}</td>
            </tr>
        </#list>
        </tbody>
    </table>
    <#include "../../partials/pagination.ftlh">
    <div class="mx-auto mt-3 w-50">
        <a type="button" class="btn btn-primary w-100" href="/doctor/medical-history/${medicalHistoryId?c}/examination-result">Добавить</a>
    </div>

</@main.renderWith>