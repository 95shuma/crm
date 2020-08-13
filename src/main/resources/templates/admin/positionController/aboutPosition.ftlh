<#import "../../partials/main.ftlh" as main />
<@main.renderWith>

    <section id="page-splash" class="page-splash">
        <div class="d-flex justify-content-center">
            <div class="mx-3">
                    <h3 class="mt-3">Направление специалиста</h3>
                        <div class="form-group">
                            <p><label for="positionName" class="col-form-label">Название направления</label></p>
                            <p>${position.name}</p>
                        </div>
                    <div class="modal-footer">
                        <a href="/admin/positions/position/${position.id?c}/update" class="btn btn-primary w-100">Редактировать</a>
                    </div>
                </div>
                </div>
            </div>
    </section>
<#--<#include "partials/pagination.ftlh">-->

</@main.renderWith>