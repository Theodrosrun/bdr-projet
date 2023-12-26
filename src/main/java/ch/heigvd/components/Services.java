package ch.heigvd.components;

public class Services {
    public static String doGet(){
        return """
                    <section class="services-section">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="services-pic">
                                        <img src="img/services/service-pic.jpg" alt="">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="service-items">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="services-item bg-gray">
                                                    <img src="img/services/service-icon-1.png" alt="">
                                                    <h4>Strategies</h4>
                                                    <p>Aenean massa. Cum sociis Theme et natoque penatibus et magnis dis part urient
                                                        montes.</p>
                                                </div>
                                                <div class="services-item bg-gray pd-b">
                                                    <img src="img/services/service-icon-3.png" alt="">
                                                    <h4>Workout</h4>
                                                    <p>Aenean massa. Cum sociis Theme et natoque penatibus et magnis dis part urient
                                                        montes.</p>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="services-item">
                                                    <img src="img/services/service-icon-2.png" alt="">
                                                    <h4>Yoga</h4>
                                                    <p>Aenean massa. Cum sociis Theme et natoque penatibus et magnis dis part urient
                                                        montes.</p>
                                                </div>
                                                <div class="services-item pd-b">
                                                    <img src="img/services/service-icon-4.png" alt="">
                                                    <h4>Weight Loss</h4>
                                                    <p>Aenean massa. Cum sociis Theme et natoque penatibus et magnis dis part urient
                                                        montes.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                """;
    }
}
