<#import "partials/main.ftlh" as main />

<@main.renderWith>
    <#include "partials/header.ftlh">
    <a href="/patientRegister"><button>Зарегистрировать пациента</button></a>
    <a href="/doctorRegister"><button>Зарегистрировать врача</button></a>
    <a href="/positions"><button>Редактировать должности врачей</button></a>
    <a href="/registration_types"><button>Редактировать</button></a>
    <#--<#include "partials/pagination.ftlh">-->
</@main.renderWith>