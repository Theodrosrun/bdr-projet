package ch.heigvd.components;

public class Title {
    public static String doGet(String title) {
        return """
                    <section class="breadcrumb-section set-bg" data-setbg="img/breadcrumb/classes-breadcrumb.jpg">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="breadcrumb-text">
                                        <h2>%s</h2>
                                        <div class="breadcrumb-option">
                                            <a href="./home"><i class="fa fa-home"></i> Home</a>
                                            <span>%s</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                """.formatted(title, title);
    }
}
