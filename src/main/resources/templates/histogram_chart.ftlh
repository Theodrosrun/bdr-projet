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
          <#list frequencies as frequency>
            <tr>
              <th scope="row">${frequency.heure}</th>
              <td style="--size: ${frequency.moyenne_personnes};"></td>
            </tr>
            </#list>
          </tbody>
        </table>
      </div>
    </div>
  </div>
</section>
