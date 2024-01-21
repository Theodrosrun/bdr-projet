<section class="register-section spad">
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="register-text">
                    <div class="section-title">
                        <h2>Welcome!</h2>
                    </div>
                    <div class="register-form">
                    <div class="row">
                        <div class="col-lg-6">
                            <label>Prénom</label>
                            <p>${account.prenom}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Nom</label>
                            <p>${account.nom}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Username</label>
                            <p>${account.username}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Email</label>
                            <p>${account.adresseMail}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Telephone</label>
                            <p>${account.numeroTelephone}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Type</label>
                            <p>${account.userType}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Adresse</label>
                            <p>${account.adresse}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Ville</label>
                            <p>${account.ville}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>NPA</label>
                            <p>${account.NPA}</p>
                        </div>
                        <div class="col-lg-6">
                            <label>Country</label>
                            <p>${account.pays}</p>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-4">
                <div class="register-pic">
                    <img id="profile-image" src="" alt="">
                </div>
            </div>
        </div>
    </div>
    <script>
        let imgElement = document.getElementById('profile-image');
        let imgSrc = "img/account/${account.username}.png"
        imgElement.src = imgSrc;
        imgElement.onerror = function() {
            imgElement.src = "img/account/default.png";
        };
    </script>
</section>
