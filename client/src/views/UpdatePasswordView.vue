<template>
  <div class="sign-in" style="background-color: #f7f7f7">
    <section class="vh-100">
      <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
          <div class="col-md-9 col-lg-6 col-xl-5">
            <img src="@/assets/images/site/dog_login.jpeg"
                 class="img-rounded" alt="Sample image" style="width: 82%;margin-left: 20px">
          </div>
          <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1" style="margin-left: 20px">
            <div v-if="!submitted">
              <form>
                <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                  <p class="lead fw-normal mb-0 me-3">Reset your Password</p>
<!--                  <button type="button" class="btn btn-dark btn-floating mx-1">-->
<!--                    <i class="fab fa-google"></i>-->
<!--                  </button>-->

<!--                  <button type="button" class="btn btn-dark btn-floating mx-1">-->
<!--                    <i class="fab fa-facebook-f"></i>-->
<!--                  </button>-->

<!--                  <button type="button" class="btn btn-dark btn-floating mx-1">-->
<!--                    <i class="fab fa-twitter"></i>-->
<!--                  </button>-->

<!--                  <button type="button" class="btn btn-dark btn-floating mx-1">-->
<!--                    <i class="fab fa-linkedin-in"></i>-->
<!--                  </button>-->
                </div>

                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0"></p>
                </div>

                <!-- Username input -->
                <div class="form-outline mb-4">
                  <input type="text" id="username" class="form-control form-control-lg"
                         placeholder="Enter username" v-model="user.userName" />
                  <!--                  <label class="form-label" for="username">Username</label>-->
                </div>

                <!-- Secret Ques input -->
                <div class="form-outline mb-4">
                  <input type="text" id="securityQues" class="form-control form-control-lg"
                         placeholder="Enter security answer - First School Name" v-model="user.securityQues" @change="validateUser"/>
                  <!--                  <label class="form-label" for="username">Username</label>-->
                </div>

                <!-- Password input -->
                <div v-if="this.validated ==='true'" class="form-outline mb-3">
                  <input type="password" id="password" class="form-control form-control-lg"
                         placeholder="Enter new password" v-model="user.password" />
                  <!--                  <label class="form-label" for="password">Password</label>-->
                </div>
                <div v-if="this.validated === 'false'" class="alert alert-danger">
                  <p>Incorrect Username or Security Answer</p>
                </div>

<!--                <div class="d-flex justify-content-between align-items-center">-->
<!--                  &lt;!&ndash; Checkbox &ndash;&gt;-->
<!--                  <div class="form-check mb-0">-->
<!--                    <input class="form-check-input me-2" type="checkbox" value="" id="remember-me-checkbox" />-->
<!--                    <label class="form-check-label" for="remember-me-checkbox">-->
<!--                      Remember me-->
<!--                    </label>-->
<!--                  </div>-->
<!--                  <a href="/" class="text-body">Forgot password?</a>-->
<!--                </div>-->

                <div class="text-center text-lg-start mt-4 pt-2">
                  <button type="button" class="btn btn-dark btn-lg" @click="resetPwd"
                          style="padding-left: 2.5rem; padding-right: 2.5rem;">Reset Password</button>
<!--                  <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <br>-->
<!--                    <a href="/sign-up">Register</a>-->
<!--                    or-->
<!--                    <a href="/"-->
<!--                    >Continue as Guest</a-->
<!--                    ></p>-->
                </div>

              </form>
            </div>
            <div v-else class="alert alert-success">
              <h4>Password has been reset successfully!</h4>
              <h4><a href="/" class="alert-link">Click to Continue</a></h4>
            </div>
            <!--            <div v-else>-->
            <!--              <h4>Logged In successfully!</h4>-->
            <!--              <router-link to="../"></router-link>-->
            <!--            </div>-->
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script  lang="ts">
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import UserDataService from "@/services/UserDataService";
import User from "@/types/User";
import { useStorage } from "vue3-storage";
export default defineComponent({
  name: "sign-in",
  data() {
    return {
      user: {
        id:null,
        userName: "",
        securityQues:"",
        password: "",
        role: "",
      } as User,
      submitted: false,
      validated: "",
      message: "",
      userData: [] as User[],

    };
  },

  methods: {

    validateUser() {

      UserDataService.validateUser(this.user.userName, this.user.securityQues)
          .then((response: ResponseData) => {
            //alert("User Logged In successfully"
            this.userData = response.data;
            this.user.id = response.data.id;
            console.log(response.data);
            console.log("response--"+response.data.id);
            if (this.userData.length > 0) {
              this.validated = 'true';
            }
          })
          .catch((e: Error) => {
            console.log(e);
            this.validated = 'false';
          });
    },

    resetPwd() {
      let data = {
        id: this.user.id,
        userName: this.user.userName,
        password: this.user.password,
      };
      UserDataService.resetPwd(data)
          .then((response: ResponseData) => {
            this.user.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
          })
          .catch((error) => {
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();

          });

    },

  },
});


</script>

<style>
.sign-in {
  background-color: #f7f7f7;
}

.divider:after,
.divider:before {
  content: "";
  flex: 1;
  height: 1px;
  background: #eee;
}

.alert-success{
  text-align: center;
}
.h-custom {
  height: calc(100% - 73px);
}
@media (max-width: 400px) {
  .h-custom {
    height: 50%;
  }
}

a,p,label {
  color: black;
}

.img-rounded {
  border-radius: 15px;
}
</style>