<section class="classes-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>UNLIMITED CLASSES</h2>
                </div>
            </div>
        </div>
        <div class="row classes-slider owl-carousel">
            <#list classes as class>
                <div class="col-lg-4">
                    <div class="single-class-item set-bg" data-setbg="${class.imageUrl}">
                        <div class="si-text">
                            <h4>${class.name}</h4>
                            <span><i class="fa fa-user"></i> ${class.instructor}</span>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</section>