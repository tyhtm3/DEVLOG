<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content" style="padding-bottom:30px">
        <div class="box" style="width:70%; margin: 0 auto; float: none;">
            <el-tabs type="border-card">
              <el-tab-pane label="Common">
                <write-default></write-default>
              </el-tab-pane>
              <el-tab-pane label="Project">
                <write-project></write-project>
              </el-tab-pane>
              <el-tab-pane style=";" label="Portfolio">
                <write-portfolio></write-portfolio>
              </el-tab-pane>
            </el-tabs>
        </div>
      </section>
    </div>
  </transition>
</template>

<script>
import writeDefault from '../components/writeDefault'
import writeProject from '../components/writeProject'
import writePortfolio from '../components/writePortfolio'

export default {
  components: {
    writeDefault,
    writeProject,
    writePortfolio
  },
}
</script> 

<style scoped>

</style>