<section class="register-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8" style="max-width: 100%; flex: none;">
                <div class="register-text">
                    <div class="section-title">
                        <h2>Register Now</h2>
                    </div>
                    <form class="register-form" action="/registermember" method="post">
                        <div class="row">
                            <#list fields as field>
                                <div class="col-lg-6">
                                    <label for="${field.inputName}">${field.labelName}</label>
                                    <#if field.inputType == "select">
                                        <select id="${field.inputName}" name="${field.inputName}" required>
                                            <#list field.options as option>
                                                <option>${option}</option>
                                            </#list>
                                        </select>
                                    <#else>
                                        <input id="${field.inputName}" name="${field.inputName}" type="${field.inputType}" required />
                                    </#if>
                                </div>
                            </#list>
                        </div>
                        <button class="register-btn" type="submit">Get Started</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>