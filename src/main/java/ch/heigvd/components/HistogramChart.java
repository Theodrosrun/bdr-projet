package ch.heigvd.components;

public class HistogramChart {
    public static String doGet() {
        return """
                                <section class="histogram spad">
                                  <div class="row">
                                    <div class="col-lg-12">
                                      <div class="section-title">
                                        <h2>Frequency</h2>
                                      </div>
                                    </div>
                                  </div>
                                  <div class="row">
                                    <div class="container">
                                      <div id="column-example-9">
                                        <table class="charts-css column show-labels show-primary-axis show-10-secondary-axes">
                                          <caption> Column Example #9 </caption>
                                          <thead>
                                            <tr>
                                              <th scope="col"> Year </th>
                                              <th scope="col"> Progress </th>
                                            </tr>
                                          </thead>
                                          <tbody>
                                            <tr>
                                              <th scope="row"> Monday </th>
                                              <td style="--size: 0.2;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Tuesday </th>
                                              <td style="--size: 0.8;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Wednesday </th>
                                              <td style="--size: 0.4;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Thursday </th>
                                              <td style="--size: 0.6;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Friday </th>
                                              <td style="--size: 0.2;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Saturday </th>
                                              <td style="--size: 0.8;"></td>
                                            </tr>
                                            <tr>
                                              <th scope="row"> Sunday </th>
                                              <td style="--size: 0.4;"></td>
                                            </tr>
                                          </tbody>
                                        </table>
                                      </div>
                                    </div>
                                  </div>
                                </section>
                """;
    }
}
