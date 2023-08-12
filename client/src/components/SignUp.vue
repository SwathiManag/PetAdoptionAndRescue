<template>
  <div class="sign-up-page">
    <!-- Section: Design Block -->
    <section class="">
      <!-- Jumbotron -->
      <div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%)">
        <div class="container" v-if="!submitted">
          <div class="row gx-lg-5 align-items-center">
            <div class="col-lg-6 mb-5 mb-lg-0">
              <h1 class="my-5 display-3 fw-bold ls-tight" style="color: black">
                Bring the animal-loving community <br />
                <span style="color:#0065b3">together</span>
              </h1>
              <p style="color: hsl(217, 10%, 50.8%)">
                LuckyPaws is a comprehensive site for animal care, pet adoption, and
                lost & found services that's designed to help more pets find their forever home.
              </p>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0">
              <div class="card">
                <div class="card-body py-5 px-md-5">
                  <form @submit.prevent="saveUser">
                    <!-- 2 column grid layout with text inputs for the first and last names -->
                    <div class="row">
                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <input type="text" id="firstName" class="form-control" placeholder="First name*" v-model="user.firstName" required />
                        </div>
                      </div>
                      <div class="col-md-6 mb-4">
                        <div class="form-outline">
                          <input type="text" id="form3Example2" class="form-control" placeholder="Last name" v-model="user.lastName" />
                        </div>
                      </div>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                      <input type="email" id="emailAddress" class="form-control" placeholder="Email address*" v-model="user.emailId" required />
                    </div>

                    <!-- Phone Number input -->
                    <div class="form-outline mb-4">
                      <input type="tel" id="phoneNumber" class="form-control" placeholder="Phone Number (format: xxxxxxxxxx)" pattern="\d{3}\d{3}\d{4}$" maxlength="12" v-model="user.phoneNumber" />
                    </div>

                    <!-- Address input -->
                    <div class="form-outline mb-4">
                      <input type="text" id="address" class="form-control" placeholder="Address" v-model="user.address" />
                    </div>

                    <!-- Username input -->
                    <div class="form-outline mb-4">
                      <input type="text" id="userName" class="form-control" placeholder="Username*" v-model="user.userName" required />
                    </div>

                    <!-- Secret Question input -->
                    <div class="form-outline mb-4">
                      <input type="text" id="securityQues" class="form-control" placeholder="Security Question - What is the name of your first school?*" v-model="user.securityQues" required />
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-4">
                      <input type="password" id="password" class="form-control" placeholder="Password*" v-model="user.password" required name="up"/>
                    </div>

                    <!-- Password input confirmation -->
                    <div class="form-outline mb-4">
                      <input type="password" id="passwordConfirm" class="form-control" placeholder="Confirm password*" required name="up2"/>
                    </div>

                    <p style="font-size: small">
                      By creating an account, you are agreeing to LuckyPaws'
                      <a
                          href="/privacy-terms"
                          style="cursor: pointer;font-size: small"
                      >Terms & Services</a
                      >
                    </p>

                    <!-- Submit button -->
                    <button type="submit" class="btn btn-dark btn-lg btn-block mb-4"
                        onclick='up2.setCustomValidity(up2.value !== up.value ? "Passwords do not match." : "")'>
                      Sign Up
                    </button>

                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
            v-if="message"
            class="alert"
            :class="successful ? 'alert-success' : 'alert-danger'"
        >
          {{ message }}
        </div>

        <div v-if="successful" class="alert alert-success">
          <h4>User Registered Successfully!</h4>
          <h4><a href="/sign-in" class="alert-link">Login to Continue</a></h4>
        </div>
      </div>
      <!-- Jumbotron -->
    </section>
    <!-- Section: Design Block -->
  </div>
</template>

<script lang="ts">

import {defineComponent} from "vue";
import UserDataService from "../services/UserDataService";
import User from "../types/User";
import ResponseData from "../types/ResponseData";

export default defineComponent({
  name: "sign-up",
  data() {
    return {
      user: {
        id: null,
        firstName: "",
        lastName: "",
        userName: "",
        password: "",
        address: "",
        emailId: "",
        phoneNumber: 0,
        securityQues:"",
      } as User,
      submitted: false,
      message: "",
      successful: false,
    };
  },
  methods: {
    saveUser() {

      let data = {
        userName: this.user.userName,
        password: this.user.password,
        emailId: this.user.emailId,
        firstName: this.user.firstName,
        lastName: this.user.lastName,
        phoneNumber: this.user.phoneNumber,
        address: this.user.address,
        message: this.message = "",
        successful: this.successful = false,
        securityQues: this.user.securityQues,

      };

      UserDataService.create(data)
          .then((response: ResponseData) => {
            this.user.id = response.data.id;
            console.log(response.data);
            this.submitted = true;
            this.successful = true;
          })
          .catch((error) => {
            this.message =
                (error.response &&
                    error.response.data &&
                    error.response.data.message) ||
                error.message ||
                error.toString();
            this.successful = false;

          });
    },
  },
});
</script>
<style scoped>
a,p,label {
  color: black;
}

span, .text-primary {
  color: #0065b3;
}
</style>
