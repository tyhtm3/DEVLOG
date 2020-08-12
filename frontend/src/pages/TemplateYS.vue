<template>

<section id="ysTemplate">

<div id="container">
  <div id ="main">
    <span id="profile_img">
    <img src='http://i3a402.p.ssafy.io/images/20200803062316_profile.jpg'>
    </span>
    <span>
    이름       : 차윤석( 24세 )
    </span>
    <span>
    관심분야   : Backend, Server
    </span>
    <span>
    Github    : https://github.com/C9Boom7
    </span>
    <br>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <!-- Default box -->
                    <div class="box">
                        <div class="box-body">
                            <ve-pie :data="chartData" :settings="chartSettings"></ve-pie>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
            </div>
             <div class="box-body no-pad padding-fifthin" style="display: block;">
                <div class="events-nest ">
                    <div class="wrap-no-pad padding-fifthin">
                        <div class="row">
                            <div class="col-sm-6 bor-right bor-bottom">
                                <div class="devin-table-dash centered pad-box"> <i class="palette-Pink-500 bg ti-shopping-cart-full"></i>
                                    <div class="palette-Pink-500 text value">648</div>
                                    <div class="label">Items Sold</div>
                                </div>
                            </div>
                            <div class="col-sm-6 bor-bottom">
                                <div class="devin-table-dash centered pad-box"> <i class="palette-Purple-500 bg ti-link"></i>
                                    <div class="palette-Purple-500 text value">47.5<strong>K</strong></div>
                                    <div class="label">Followers</div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6 bor-right">
                                <div class="devin-table-dash centered pad-box"> <i class="palette-Deep-Purple-500 bg ti-wallet"></i>
                                    <div class="value palette-Deep-Purple-500 text"><strong>$</strong>764</div>
                                    <div class="label">Daily Earnings</div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="devin-table-dash centered pad-box"> <i class="ti-bag palette-Light-Blue-500 bg"></i>
                                    <div class="value palette-Light-Blue-500 text">256</div>
                                    <div class="label">Products</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
  </div>
  <div class="box-body" style="min-height:400px;">
        <div class="container-timeline">
            <div class="header-timeline">
                <svg xmlns="http://www.w3.org/2000/svg" height="64" width="64">
                    <defs>
                        <linearGradient id="verlauf" x1="0%" y1="0%" x2="100%" y2="100%">
                            <stop offset="0%" stop-color="orange" />
                            <stop offset="100%" stop-color="chocolate" /> </linearGradient>
                    </defs>
                    <path fill="url(#verlauf)" d="M63.04 39.74C58.764 56.885 41.4 67.318 24.256 63.043 7.12 58.768-3.313 41.404.962 24.262 5.234 7.117 22.597-3.318 39.737.957 56.88 5.23 67.313 22.597 63.04 39.74z" />
                    <path fill="#FFF" d="M46.11 27.44c.636-4.257-2.606-6.546-7.04-8.073l1.44-5.768-3.512-.876-1.4 5.616c-.923-.23-1.87-.447-2.813-.662l1.41-5.653-3.51-.875-1.438 5.766c-.764-.174-1.514-.346-2.242-.527l.004-.02-4.843-1.208-.934 3.75s2.605.597 2.55.634c1.422.355 1.68 1.296 1.636 2.042l-1.64 6.57c.1.026.226.062.366.118l-.37-.092-2.297 9.205c-.175.43-.616 1.08-1.61.833.035.05-2.552-.637-2.552-.637l-1.743 4.02 4.57 1.138c.85.213 1.682.436 2.502.646l-1.453 5.834 3.507.875 1.44-5.773c.958.26 1.888.5 2.798.726l-1.434 5.745 3.51.875 1.454-5.823c5.987 1.133 10.49.676 12.384-4.74 1.527-4.36-.076-6.874-3.226-8.514 2.294-.53 4.022-2.038 4.483-5.155zm-8.023 11.25c-1.085 4.36-8.426 2.003-10.806 1.412l1.93-7.73c2.38.595 10.01 1.77 8.877 6.318zm1.086-11.312c-.99 3.966-7.1 1.95-9.082 1.457l1.75-7.01c1.98.494 8.364 1.416 7.333 5.553z" /> </svg>
                <h1>History of bitcoin</h1>
                <h2>A timeline that shows bitcoin History from the idea all the way to present day.</h2> </div>
            <div class="item-timeline">
                <div id="timeline">
                    <div>
                        <section class="year">
                            <h3>2007</h3>
                            <section>
                                <ul>
                                    <li>Satoshi Nakamoto began working on the Bitcoin concept.</li>
                                </ul>
                            </section>
                        </section>
                        <section class="year">
                            <h3>2016</h3>
                            <section>
                                <h4>January</h4>
                                <ul>
                                    <li>Price at <span class="price">$1150</span> per bitcoin.</li>
                                    <li>Price fell 30% in a week, reaching a multi-month low of <span class="price">$750.</span></li>
                                    <li>The network exceeded 1 exahash/sec.</li>
                                </ul>
                            </section>
                            <section>
                                <h4>February</h4>
                                <ul>
                                    <li>Version 0.12 released.</li>
                                </ul>
                            </section>
                            <section>
                                <h4>April</h4>
                                <ul>
                                    <li>Steam started accepting bitcoin as payment.</li>
                                </ul>
                            </section>
                            <section>
                                <h4>August</h4>
                                <ul>
                                    <li>Version 0.13 released.</li>
                                </ul>
                            </section>
                            <section>
                                <h4>September</h4>
                                <ul>
                                    <li>There are 770 bitcoin ATMs worldwide.</li>
                                </ul>
                            </section>
                        </section>
                        <section class="year">
                            <h3>2017</h3>
                            <section>
                                <h4>March</h4>
                                <ul>
                                    <li>Version 0.14 released.</li>
                                    <li>The price of 1 bitcoin surpassed the spot price of an ounce of gold for the first time.</li>
                                    <li>The number of GitHub projects related to bitcoin passed 10,000.</li>
                                    <li>Price traded above <span class="price">$1290.</span></li>
                                </ul>
                            </section>
                            <section>
                                <h4>Today</h4>
                                <ul>
                                    <li>At the moment, one bitcoin cost: <span class="price"><div id="btcwidget"></div>.</span></li>
                                </ul>
                            </section>
                        </section>
                    </div>
                </div>
            </div>
        </div>  
    </div>


</div>
  
</section>

</template>

<script>
export default{
  date(){
  },
   mounted(){
    $(".main-header").css('display','none')
    $(".main-footer"). css('display','none')
  },
  created: function () {
      this.chartData = {
        rows: [
          { 'stack': 'Spring', 'rate': 5 },
          { 'stack': 'Java', 'rate': 4 },
          { 'stack': 'MariaDB', 'rate': 3 },
          { 'stack': 'AWS', 'rate': 3 },
          { 'stack': 'Vue.js', 'rate': 1 }
        ]
      }
      this.chartSettings = {
        dimension: 'stack',
        metrics: 'rate'
      }
    }
}
</script>

<style lang="scss" scoped>

#main{
  position: relative;
  margin-left : 20%;
  margin-right : 20%;
  background-color : #E6E6E6;
}
.tl-item {
  transform: translate3d(0, 0, 0);
  position: relative;
  width: 25%;
  height: 100vh;
  min-height: 600px;
  color: #fff;
  overflow: hidden;
  transition: width 0.5s ease;
  
  &:before, &:after {
    transform: translate3d(0, 0, 0);
    content: '';
    position: absolute;
    left: 0; top: 0;
    width: 100%; height: 100%;
  }

  &:after {
    background: transparentize(#031625, 0.15);
    opacity: 1;
    transition: opacity 0.5s ease;
  }

  &:before {
    background: linear-gradient(to bottom, rgba(0,0,0,0) 0%,rgba(0,0,0,1) 75%);
    z-index: 1;
    opacity: 0;
    transform: translate3d(0, 0, 0) translateY(50%);
    transition: opacity 0.5s ease, transform 0.5s ease;
  }

  &:hover {
    width: 30% !important;
    
    &:after {
      opacity: 0;
    }

    &:before {
      opacity: 1;
      transform: translate3d(0, 0, 0) translateY(0);
      transition: opacity 1s ease, transform 1s ease 0.25s;
    }
    
    .tl-content {
      opacity: 1;
      transform: translateY(0);
      transition: all 0.75s ease 0.5s;
    }

    .tl-bg {
      filter: grayscale(0);
    }
  }
}

.tl-content {
  transform: translate3d(0, 0, 0) translateY(25px);
  position: relative;
  z-index: 1;
  text-align: center;
  margin: 0 1.618em;
  top: 55%;
  opacity: 0;
  
  h1 {
    font-family: 'Pathway Gothic One',Helvetica Neue,Helvetica,Arial,sans-serif;
    text-transform: uppercase;
    color: #1779cf;
    font-size: 1.44rem;
    font-weight: normal;
  }
}

.tl-year {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translateX(-50%) translateY(-50%);
  z-index: 1;
  border-top: 1px solid #fff;
  border-bottom: 1px solid #fff;
  
  p {
    font-family: 'Pathway Gothic One',Helvetica Neue,Helvetica,Arial,sans-serif;
    font-size: 1.728rem;
    line-height: 1.4;
    margin: 0px;
  }
}

.tl-bg {
  transform: translate3d(0, 0, 0);
  position: absolute;
  width: 100%; height: 100%;
  top: 0; left: 0;
  background-size: cover;
  background-position: center center;
  transition: filter 0.5s ease;
  filter: grayscale(100%);
}
.padding-fifthin{
  padding: 15px!important;
}
</style>