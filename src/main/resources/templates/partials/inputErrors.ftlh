<#if errors??>
    <#assign fieldNames = errors?map(fieldError -> fieldError.field)>
    <#assign fieldErrors = errors?filter(fieldError -> fieldError.field == fieldName)>
    <#if fieldNames?seq_contains(fieldName)>
        ${input?no_esc}
        <#list fieldErrors as fieldError>
            <#if fieldError.field == fieldName>
                <div class="alert alert-warning mt-1" role="alert">${fieldError.defaultMessage!'-- no message --'}</div>
            </#if>
        </#list>
    </#if>
    <#if !fieldNames?seq_contains(fieldName)>
        ${inputWithValue?no_esc}
    </#if>
<#else>
        ${input?no_esc}
</#if>
