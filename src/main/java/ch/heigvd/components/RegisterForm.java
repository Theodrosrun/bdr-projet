package ch.heigvd.components;

public class RegisterForm {
    public static String doGet(){
        return """
                    <section class="register-section spad">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-8">
                                    <div class="register-text">
                                        <div class="section-title">
                                            <h2>Register Now</h2>
                                            <p>The First 7 Day Trial Is Completely Free With The Teacher</p>
                                        </div>
                                        <form action="#" class="register-form">
                                            <div class="row">
                                                <div class="col-lg-6">
                                                    <label for="name">First Name</label>
                                                    <input type="text" id="name">
                                                </div>
                                                <div class="col-lg-6">
                                                    <label for="email">Your email address</label>
                                                    <input type="text" id="email">
                                                </div>
                                                <div class="col-lg-6">
                                                    <label for="last-name">Last Name</label>
                                                    <input type="text" id="last-name">
                                                </div>
                                                <div class="col-lg-6">
                                                    <label for="mobile">Mobile No*</label>
                                                    <input type="text" id="mobile">
                                                </div>
                                            </div>
                                            <button type="submit" class="register-btn">Get Started</button>
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