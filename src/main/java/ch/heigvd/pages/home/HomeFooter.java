package ch.heigvd.pages.home;

public class HomeFooter {
    public static String doGet() {
        return """
                    <section class="footer-banner">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="footer-banner-item set-bg" data-setbg="img/footer-banner/footer-banner-1.jpg">
                                        <span>New member</span>
                                        <h2>7 days for free</h2>
                                        <p>Complete the training sessions with us, surely you will be happy</p>
                                        <a href="#" class="primary-btn">Get Started</a>
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="footer-banner-item set-bg" data-setbg="img/footer-banner/footer-banner-2.jpg">
                                        <span>contact us</span>
                                        <h2>09 746 204</h2>
                                        <p>If you trust us on your journey they dark sex does not disappoint you!</p>
                                        <a href="#" class="primary-btn">Get Started</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                """;
    }
}
