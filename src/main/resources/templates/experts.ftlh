<section class="trainer-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="section-title">
                    <h2>${title}</h2>
                </div>
            </div>
        </div>
        <div class="row">
            <#list experts as expert>
                <div class="col-lg-4 col-md-6">
                    <div class="single-trainer-item">
                        <img src="img/account/${expert.compte}.png" alt="" width="290px" >
                        <div class="trainer-text">
                            <h5>${expert.instructeur}</h5>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</section>