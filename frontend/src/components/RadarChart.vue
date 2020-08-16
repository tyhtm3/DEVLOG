
<script>
  import { Radar } from 'vue-chartjs'

  export default {
    extends: Radar,
    props: ['label','exp','title'],
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
              label: 'Tech Ability',
              borderWidth: 1,
              backgroundColor: 'rgba(54, 162, 235, 0.2)',
             
              data: [100,100,100,100,100,100,100,100,100]
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
    },
    mounted () {
      setTimeout(()=>{
      this.chartData.labels=this.label
      this.chartData.datasets[0].data = this.exp
      this.chartData.datasets[0].label = this.title
      this.renderChart(this.chartData, this.options)},500)
    }
  }
</script>