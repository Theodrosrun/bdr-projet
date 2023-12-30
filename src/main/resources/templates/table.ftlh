<section class="classes-timetable spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>${title}</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="schedule-table">
                    <table>
                        <thead>
                        <tr>
                            <#list headers as header>
                            <th>${header}</th>
                            </#list>
                        </tr>
                        </thead>
                        <tbody>
                        <#list entries as entry>
                            <tr>
                                <#list entry as e>
                                    <td>${e}</td>
                                </#list>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>