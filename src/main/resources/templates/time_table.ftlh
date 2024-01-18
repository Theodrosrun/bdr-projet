<section class="classes-timetable spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>Class Timetable</h2>
                </div>
                <div class="nav-controls">
                    <ul>
                        <li class="active" data-tsfilter="all">All Class</li>
                        <#list typeOfCours as type>
                            <li data-tsfilter="${type}">${type}</li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="schedule-table">
                    <table>
                        <thead>
                        <tr>
                            <th></th>
                            <#list jours as jour>
                                <th>${jour}</th>
                            </#list>
                        </tr>
                        </thead>
                        <tbody>
                        <#assign delimiter = " - ">
                        <#list rows as row>
                            <tr>
                                <#list row as cell>
                                    <#if cell?index == 0>
                                        <td class="workout-time">${(cell)!""}</td>
                                    <#else>
                                        <#if cell??>
                                            <#assign myString = cell>
                                            <#assign myArray = myString?split(delimiter)>
                                            <td class="hover-bg ts-item" data-tsmeta="${(myArray[0])!""}">
                                                <h6>${(myArray[0])!""}</h6>
                                                <span>${(myArray[2])!""}</span>
                                                <div class="trainer-name">
                                                    ${(myArray[1])!""}
                                                </div>
                                            </td>
                                        <#else>
                                            <td class="hover-bg ts-item" data-tsmeta="">
                                                <h6></h6>
                                                <span></span>
                                                <div class="trainer-name">
                                                </div>
                                            </td>
                                        </#if>
                                    </#if>
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