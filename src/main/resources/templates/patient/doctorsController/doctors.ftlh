<#import "../../partials/main.ftlh" as main />

<@main.renderWith>
    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-auto mt-3 w-50">
                <h3 class="mt-3">Список ЛПУ</h3>
                <input class="form-control" id="Input" type="text" placeholder="Поиск по всем полям...">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">ФИО</th>
                        <th scope="col">Направление</th>
                    </tr>
                    </thead>
                    <tbody id="Table">
                    <#list items as hospital>
                        <tr>
                            <th scope="row">${hospital?counter}</th>
                            <td>${doctors.user.fullName}</td>
                            <td>${doctors.position.name}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</@main.renderWith>

<script>
    $(document).ready(function(){
        $("#Input").on("keyup", function() {
            var value = $(this).val().toLowerCase();
            $("#Table tr").filter(function() {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>