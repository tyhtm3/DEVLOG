<template>
	<transition name="el-zoom-in-top">
    <div class="content-wrapper">
      <!-- start Main content -->
      <section class="content">
        <div class="box">
          <div class="box-body" style="align:center; min-height:400px; max-width:800px; margin:auto">
						<div class="col-sm-12">
							<br>
							<br>
							<br>
							<div class="col-xs-12 col-sm-12 profile-name">
								<span style="font-size:30px;">아이디 찾기</span>
                <hr>
                <span style="font-size:12px;">계정의 이메일 주소를 통해 아이디를 찾을 수 있습니다. 전체 아이디 정보는 이메일로 전송됩니다.</span><br>
                 
                  <br><br>
                  <div class="row">
                  <div class="col-sm-3"><p style="margin-top:8px;">이름</p></div>
                  <div class="col-sm-9"><el-input v-model="nameForId" style="width: 60%;"></el-input></div>
                  </div>

                  <br><br>
                  <div class="row">
                  <div class="col-sm-3"><p style="margin-top:8px;">이메일 주소</p></div>
                  <div class="col-sm-9"><el-input v-model="emailForId" style="width: 60%;"></el-input>
                  <el-button :plain="true" style="margin-left:20px;" @click="findid">아이디 찾기</el-button></div>

                     <el-dialog :visible.sync="isModalVisible" height="500" >
                        <h1 style="align:center;">아이디는 {{id}} 입니다.</h1>
                        
                        <el-button style="align:right;" type="primary" @click="findfullid">전체 아이디 메일 발송</el-button>
                        
                    </el-dialog>

                  </div>
                       


        
                </div>
              
             	<div class="col-xs-12 col-sm-12 profile-name" style="margin-top:50px;margin-bottom:50px;">
								<span style="font-size:30px;">비밀번호 찾기</span>
                <hr>
                <span style="font-size:12px;">계정의 이메일로 임시 비밀번호를 발급합니다.</span><br>
                 
                  <br><br>
                  <div class="row">
                  <div class="col-sm-3"><p style="margin-top:8px;">이메일 주소</p></div>
                  <div class="col-sm-9"><el-input v-model="emailForPassword" style="width: 60%;"></el-input>
                  <el-button :plain="true" style="margin-left:20px;" @click="findpwd">비밀번호 전송</el-button></div>
                  </div>


        
                </div>


              </div>
						</div>
        </div>
      </section>
      
    </div>
    
  </transition>
</template>

<script>
import http from '../util/http-common'
export default {
  data: () => {
		return {
      nameForId: '',
      emailForId: '',
      emailForPassword: '',
      id:'',
      isModalVisible: false,
		}
	},
  created(){
    this.$store.state.loginFormVisible = false;
  },
  methods: {
    // 아이디 찾기 (ex.abcd****)
    findid(){
      if(this.nameForId=='')
          this.$message.warning('이름을 입력해주세요.')
      else if(this.emailForId=='')
          this.$message.warning('이메일을 입력해 주세요.')
      else{
      http.get(`/user/email/${this.emailForId}`)
      .then(({ data }) => {
            if(data.length!==0 && data.name===this.nameForId){
              this.id=this.hideId(data.id)
              this.isModalVisible=true
            }else{
               this.$message({
                type: 'warning',
                message: '이름 또는 이메일 주소를 다시 입력해주세요.'
              });
            }
        })
          }
    },
    // 아이디 정보 *표시 없이 메일로 전송
    findfullid(){
      http.get(`/user/findid?email=${this.emailForId}`)
      .then(({ data }) => {
           this.$message({
                type: 'success',
                message: '아이디가 이메일로 전송되었습니다.'
            }) })
    },
    // 임시 비밀번호 발급 메일 전송
    findpwd(){
        if(this.emailForPassword=='')
          this.$message.warning('이메일을 입력해주세요.')
        else{
        http.get(`/user/findpwd?email=${this.emailForPassword}`)
        .then(({ data }) => {
            this.$message({
                type: 'success',
                message: '임시 비밀번호가 발급되었습니다.'
              });
        }).catch((error) => {
            if(error.response.status=='404'){
              this.$message({
                type: 'warning',
                message: '존재하지 않는 계정입니다.'
              });
            }
          })
        }
    },
    hideId(id){
      let newId=''
      for(var i=0;i<id.length-3;i++)
          newId = newId+id.substring(i,i+1)
      
      for(var i=id.length-3;i<id.length;i++)
          newId = newId+ '*'
      return newId
    }

  }
}
</script>

<style scoped>
span{
   font-family: 'Pathway Gothic One',Helvetica Neue,Helvetica,Arial,sans-serif;
}
</style>