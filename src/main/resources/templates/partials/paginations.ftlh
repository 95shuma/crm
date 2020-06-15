<nav>
    <ul class="pagination">
        <#if hasFirst>
            <li class="page-item"><a class="page-link" href="${firstPageLink!''}">Первая</a></li>
        <#else>
            <li class="page-item disable"><a class="page-link" href="${firstPageLink!''}">Первая</a></li>
        </#if>
        <#if hasPrev>
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Предыдущая">
                    <span aria-hidden="true">«</span>
                    <span class="sr-only">Предыдущая</span>
                </a>
            </li>
        <#else>
            <li class="page-item disabled">
                <a class="page-link" href="#" aria-label="Предыдущая">
                    <span aria-hidden="true">«</span>
                    <span class="sr-only">Предыдущая</span>
                </a>
            </li>
        </#if>
        <#if hasLast>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
        <#else>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
        </#if>
        <#if hasLast>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
        <#else>
            <li class="page-item"><a class="page-link" href="#">2</a></li>
        </#if>
        <#if hasLast>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
        <#else>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
        </#if>
        <#if hasNext>
            <li class="page-item">
                <a class="page-link" href="#" aria-label="Следующая">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Следующая</span>
                </a>
            </li>
        <#else>
            <li class="page-item ">
                <a class="page-link" href="#" aria-label="Следующая">
                    <span aria-hidden="true">»</span>
                    <span class="sr-only">Следующая</span>
                </a>
            </li>
        </#if>
        <#if hasLast>
            <li class="page-item"><a class="page-link" href="#">Последняя</a></li>
        <#else>
            <li class="page-item disable"><a class="page-link" href="#">Последняя</a></li>
        </#if>
    </ul>
</nav>