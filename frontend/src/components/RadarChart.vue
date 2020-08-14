
<script>
  import { Radar } from 'vue-chartjs'

  export default {
    extends: Radar,
    props: ['label','exp'],
    data () {
      return {
        chartData: {
          labels: [
             "Babol",	
             "Cabanatuan",	
             "Daegu",	
             "Jerusalem",	
             "Fairfield",	
          ],
          datasets: [
            {
              label: 'Ability',
              borderWidth: 1,
              backgroundColor: 'rgba(54, 162, 235, 0.2)',
             
              data: [10,40,100]
            }
          ]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false
        }
      }
    },
    created(){
       this.chartData.labels=this.label
       
    },
    mounted () {
      this.renderChart(this.chartData, this.options)
    }
  }
</script>