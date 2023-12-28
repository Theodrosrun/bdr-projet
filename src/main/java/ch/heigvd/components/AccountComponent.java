package ch.heigvd.components;

import ch.heigvd.utils.structure.Account;

public class MyAccount {
    public static String doGet(Account account){
        return """
                     <section class="register-section spad">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="register-text">
                                        <div class="section-title">
                                            <h2>Sign in</h2>
                                        </div>
                                        <div class="row">
                                            <div class="col-lg-6">
                                                <label for="name">Your username</label>
                                                <input type="text" id="username" name="username" value="%s" disabled>
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
