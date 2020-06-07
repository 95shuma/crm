<#import "partials/main.ftlh" as main>
<@main.renderWith styles=["css/styles.css"]>
<#--<@main.renderWith scripts=["scripts/login.js"]>-->
    <#include "partials/header.ftlh">
    <div class="mx-auto mt-3 w-50">
        <form id="login-form" action="/login" method="post">
<#--    <form id="login-form">-->

            <#if _csrf??>
                <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
            </#if>

            <h3>Login</h3>
            <div class="form-group">
                <label>Введите ИНН:</label>
                <input type="text" name="username" class="form-control mb-1" placeholder="INN" autofocus>
            </div>
            <div class="form-group">
                <label>Введите пароль:</label>
                <input type="password" name="password" class="form-control mb-1" placeholder="Password">
            </div>
            <button class="btn btn-outline-primary btn-block">Login</button>

            <div>
                <#if error!false >
                    <p style="color:red">Error in your input data</p>
                </#if>
            </div>
        </form>
    </div>
</@main.renderWith>