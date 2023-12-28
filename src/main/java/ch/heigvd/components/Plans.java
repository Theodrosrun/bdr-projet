package ch.heigvd.components;
import java.util.HashMap;
import java.util.List;

public class Plans {
    public static String getPlans(List<HashMap<String, String>> plans, boolean withInfo){
        StringBuilder sb = new StringBuilder();
        String info = withInfo ? """
                                                            <ul>
                                                                <li>
                                                                    <p>Duration</p>
                                                                    <span>1/6/12 months</span>
                                                                </li>
                                                            </ul>
                                                            <a href="#" class="primary-btn membership-btn">Start Now</a>
                                    """ : "";
        for (HashMap<String, String> plan : plans) {
            sb.append("""
                                                    <div class="col-lg-4">
                                                        <div class="membership-item">
                                                            <div class="mi-title">
                                                                <h4>%s</h4>
                                                                <div class="triangle"></div>
                                                            </div>
                                                            <h2 class="mi-price">%s<span>/month</span></h2>
                                                            %s
                                                        </div>
                                                    </div>
                    """.formatted(plan.get("abo_id"), plan.get("prix"), info));
        }
        return sb.toString();
    }
    public static String doGet(String title, List<HashMap<String, String>> plans, boolean withInfo) {
        return """
                    <section class="membership-section spad">
                        <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="section-title">
                                        <h2>%s</h2>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                            %s
                            </div>
                        </div>
                    </section>
                """.formatted(title, getPlans(plans, withInfo));
    }
}
