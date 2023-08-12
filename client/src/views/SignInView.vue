<template>
  <div class="sign-in">
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
                  <p class="lead fw-normal mb-0 me-3">Sign in with</p>
                  <button type="button" class="btn btn-dark btn-floating mx-1">
                    <i class="fab fa-google"></i>
                  </button>

                  <button type="button" class="btn btn-dark btn-floating mx-1">
                    <i class="fab fa-facebook-f"></i>
                  </button>

                  <button type="button" class="btn btn-dark btn-floating mx-1">
                    <i class="fab fa-twitter"></i>
                  </button>

                  <button type="button" class="btn btn-dark btn-floating mx-1">
                    <i class="fab fa-linkedin-in"></i>
                  </button>
                </div>

                <div class="divider d-flex align-items-center my-4">
                  <p class="text-center fw-bold mx-3 mb-0">Or</p>
                </div>

                <!-- Username input -->
                <div class="form-outline mb-4">
                  <input type="text" id="username" class="form-control form-control-lg"
                         placeholder="Enter a valid username" v-model="user.userName" @click="clearMessage"/>
<!--                  <label class="form-label" for="username">Username</label>-->
                </div>

                <!-- Password input -->
                <div class="form-outline mb-3">
                  <input type="password" id="password" class="form-control form-control-lg"
                         placeholder="Enter password" v-model="user.password" @click="clearMessage"/>
<!--                  <label class="form-label" for="password">Password</label>-->
                </div>

                <div class="d-flex justify-content-between align-items-center">
                  <!-- Checkbox -->
                  <div class="form-check mb-0">
                    <input class="form-check-input me-2" type="checkbox" value="" id="remember-me-checkbox" />
                    <label class="form-check-label" for="remember-me-checkbox">
                      Remember me
                    </label>
                  </div>
                  <a href="/update-password" class="text-body">Forgot password?</a>
                </div>

                <div class="text-center text-lg-start mt-4 pt-2">
                  <button type="button" class="btn btn-dark btn-lg" @click="loginUser"
                          style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                  <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <br>
                    <a href="/sign-up">Register</a>
                    or
                    <a href="/"
                    >Continue as Guest</a
                    ></p>
                </div>

              </form>
            </div>
            <div v-else class="alert alert-success">
              <h4>{{this.user.userName}} Logged in Successfully!</h4>
              <h4><a href="/" class="alert-link">Click to Continue</a></h4>
            </div>
            <div v-if="message" class="alert alert-danger">
              <h4>{{message}}</h4>
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
        id: null,
        userName: "",
        password: "",
        role: "",
      } as User,
      submitted: false,
      message: "",

    };
  },

  methods: {

    loginUser() {
      let data = {
        username:  this.user.userName,
        password: this.user.password,
      };
      UserDataService.loginUser(data)
          .then((response: ResponseData) => {
            //alert("User Logged In successfully"
            this.user.userName = response.data.username;
            this.user.id = response.data.id;
            this.user.role = response.data.role;
            console.log(response.data);
            this.submitted = true;
            const storage = useStorage();
            storage.setStorageSync("user_id", response.data.id);
            storage.setStorageSync("role", response.data.role);
            //this.$router.push('/');
          })
          .catch((error) => {
            this.message = "Bad Credentials";
            this.user.userName="";
            this.user.password="";
            //this.$router.go(0);
          });
    },

    clearMessage(){
      this.message ="";
    }

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