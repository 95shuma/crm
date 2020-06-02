<header>
    <nav class="site-header sticky-top py-1">
        <div class="container d-flex flex-column flex-md-row justify-content-between">
            <a class="py-2 d-none d-md-inline-block" href="/">Subjects</a>
            <#if isLoggedIn??>
                <a class="py-2 d-none d-md-inline-block" href="/profile">Profile</a>
                <form class="flex box-128" action="/logout" method="post">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf'}" value="${(_csrf.token)!'--no-token--'}"/>
                    </#if>
                    <button class="py-2 d-none d-md-inline-block logout-button">
                        Log out
                    </button>
                </form>
            <#else>
                <a class="py-2 d-none d-md-inline-block" href="/register">Register</a>
                <a class="py-2 d-none d-md-inline-block" href="/login">Log in</a>
            </#if>
        </div>
    </nav>
</header>
