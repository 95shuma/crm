<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#if dto??>
        <h1>Your data</h1>
        <p>${(dto.inn)!'-- missing --'}</p>
    <#else>
        <h1>Найден пациент</h1>
    </#if>
</@main.renderWith>