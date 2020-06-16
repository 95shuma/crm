<#if totalPages gt 7>
    <#assign

    pageNum=pageNumber + 1

    head = (pageNum > 4)?then([1, -1], [1, 2, 3])
    tail = (pageNum < totalPages - 3)?then([-1, totalPages], [totalPages - 2, totalPages - 1, totalPages])
    bodyBefore = (pageNum > 4 && pageNum < totalPages - 1)?then([pageNum - 2, pageNum - 1], [])
    bodyAfter = (pageNum > 2 && pageNum < totalPages - 3)?then([pageNum + 1, pageNum + 2], [])

    body = head + bodyBefore + (pageNum > 3 && pageNum < totalPages - 2)?then([pageNum], []) + bodyAfter + tail
    >
<#else>
    <#assign body = 1..totalPages>
</#if>
<div class="mt-3">
    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Страницы</a>
        </li>
        <#if hasPrev>
            <li class="page-item">
                <a class="page-link"  id="loadPrev" href="${prevPageLink!''}" aria-label="Предыдущая">
                    <span aria-hidden="true">«</span>
                    <span class="sr-only">Предыдущая</span>
                </a>
            </li>
        <#else>
            <li class="page-item disabled">
                <a class="page-link"  id="loadPrev" href="${prevPageLink!''}" aria-label="Предыдущая">
                    <span aria-hidden="true">«</span>
                    <span class="sr-only">Предыдущая</span>
                </a>
            </li>
        </#if>
        <#list body as p>
            <#if (p - 1) == pageNumber>
                <li class="page-item active">
                    <a class="page-link" href="#" tabindex="-1">${p}</a>
                </li>
            <#elseif p == -1>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">...</a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="${url}?page=${p - 1}&size=${pageSize}" tabindex="-1">${p}</a>
                </li>
            </#if>
        </#list>
        <#if hasNext>
            <li class="page-item">
                <a class="page-link" id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}" aria-label="Следующая">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Следующая</span>
                </a>
            </li>
        <#else>
            <li class="page-item disabled">
                <a class="page-link" id="loadNext" href="${nextPageLink!''}" data-default-page-size="${defaultPageSize!5}" aria-label="Следующая">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Следующая</span>
                </a>
            </li>
        </#if>
    </ul>

    <ul class="pagination">
        <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1">Элементов на странице</a>
        </li>
        <#list [5, 10, 25, 50] as c>
            <#if c == pageSize>
                <li class="page-item active">
                    <a class="page-link" href="#" tabindex="-1">${c}</a>
                </li>
            <#else>
                <li class="page-item">
                    <a class="page-link" href="?page=${pageNumber}&size=${c}" tabindex="-1">${c}</a>
                </li>
            </#if>
        </#list>
    </ul>
</div>