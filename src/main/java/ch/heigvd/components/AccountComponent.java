package ch.heigvd.components;

import ch.heigvd.utils.structure.Account;

public class AccountComponent {
    public static String doGet(Account account){
        return """
                     <section class="register-section spad">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="register-text">
                                        <div class="section-title">
                                            <h2>%s</h2>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-6">
                                            <-- TODO: Add account information -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-4">
                                    <div class="register-pic">
                                        <img src="img/register-pic.jpg" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                """.formatted(account.getUsername());
    }
}
