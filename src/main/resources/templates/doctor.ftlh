<#import "partials/main.ftlh" as main>
<@main.renderWith styles=["css/styles.css"]>
<#--<@main.renderWith scripts=["scripts/login.js"]>-->
    <#include "partials/header.ftlh">
    <div class="mx-auto mt-3 w-50">
        <div>
            <a href="/patient">Войти как пациент</a>
        </div>
    </div>
</@main.renderWith>