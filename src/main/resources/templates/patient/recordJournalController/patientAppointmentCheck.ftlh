<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
        <h4 style="text-align: center">Запомните этот код и покажите его в регистратуру: <p>${random.randomUUID()}</p></h4>
    <div class="mx-auto mt-3 w-50">
        <a href="/patient" style="margin-top: 5px" class="btn btn-primary btn-block">Выйти в личный кабинет</a>
    </div>
</@main.renderWith>