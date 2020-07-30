<template>
  <transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <section class="content">
        <div class="box" style="width:70%; margin: 0 auto; float: none; margin-bottom:5px">
          <div class="box-header">
          </div>
          <div class="box-body">
            <h3>제목 :</h3>
            <input class="title">
            <vue-editor v-model="content" min-height="600px"></vue-editor>
            <div>
              <p>태그</p>
            </div>
            <div>
              <p>공개여부</p>
            </div>
            <div>
              <p>예약</p>
            </div>
            <div class="box-tools pull-right">
              <p>글쓰기</p>
            </div>
          </div>
        </div>
      </section>
    </div>
  </transition>
</template>

<script>
import { VueEditor } from 'vue2-editor'

export default {
  components: {
    VueEditor
  },
  data: function () {
    return {
      title: '',
      content: '',
      tags: {},
    }
  }
}
</script>

<style>
.ql-editor{
  min-height: 600px;
}
.title{
  height: 60px;
  width: 100%;
  font-size: 20pt;
  padding: 10px;
  border-width: 1px;
  margin-bottom: 20px;
  /* border: 1px; */
  border-color: #ccc;
}
</style>>