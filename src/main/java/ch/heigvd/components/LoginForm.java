package ch.heigvd.components;

public class LoginForm {
    public static String doGet(){
        return """
                     <section class="register-section spad">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="register-text">
                                        <div class="section-title">
                                            <h2>Sign in</h2>
                                        </div>
                                        <form action="#" class="register-form">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label for="name">Your username</label>
                                                    <input type="text" id="username">
                                                </div>
                                                <div class="col-lg-6">
                                                    <label for="email">Your password</label>
                                                    <input type="password" id="password">
                                                </div>
                                            </div>
                                            <button type="submit" class="register-btn">Login</button>
                                        </form>
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
                """;
    }
}
