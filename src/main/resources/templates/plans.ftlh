<section class="membership-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>${title}</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <#list plans as plan>
                <div class="col-lg-4">
                    <div class="membership-item">
                        <div class="mi-title">
                            <h4>${plan.abo_id}</h4>
                            <div class="triangle"></div>
                        </div>
                        <h2 class="mi-price">${plan.prix}<span>/month</span></h2>
                        <#if withInfo>
                            <ul>
                                <li>
                                    <p>Duration</p>
                                    <span>1/6/12 months</span>
                                </li>
                            </ul>
                            <a href="#" class="primary-btn membership-btn">Start Now</a>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</section>