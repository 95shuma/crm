<#import "/spring.ftl" as spring>
<#macro renderWith title="Med - CRM" scripts=[] styles=[] categories=[] dir="ltr">
    <!doctype html>
    <html lang="${.locale}" dir="${dir}">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!--<#if _csrf??>
        <meta name="_csrf" content="${_csrf.parameterName}"/>
        <meta name="_csrf_token" content="${_csrf.token}"/>
        </#if> -->
        <title>${title}</title>
        <#list styles as styleSource>
            <link rel="stylesheet" href="${styleSource}">
        </#list>
        <!-- Navbar CSS -->
        <link rel="stylesheet" href="/css/styles.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <meta name="theme-color" content="#563d7c">
        <!-- Jquery -->
        <script
                src="https://code.jquery.com/jquery-3.5.0.js"
                integrity="sha256-r/AaFHrszJtwpe+tHyNi/XCfMxYpbsRg2Uqn0x3s2zc="
                crossorigin="anonymous"></script>
        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                -ms-user-select: none;
                user-select: none;
            }
            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>

    </head>
    <body>
    <#include "header.ftlh">
        <div class="container">
            <#nested>
        </div>
        <#list scripts as scriptSource>
            <script src="${scriptSource}"></script>
        </#list>

        </body>
    </html>

</#macro>