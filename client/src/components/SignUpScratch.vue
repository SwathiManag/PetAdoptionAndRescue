<!-- Section: Design Block -->
<section class="">
<!-- Jumbotron -->
<div class="px-4 py-5 px-md-5 text-center text-lg-start" style="background-color: hsl(0, 0%, 96%)">
  <div class="container" v-if="!submitted">
    <div class="row gx-lg-5 align-items-center">
      <div class="col-lg-6 mb-5 mb-lg-0">
        <h1 class="my-5 display-3 fw-bold ls-tight">
          The best offer <br />
          <span class="text-primary">for your business</span>
        </h1>
        <p style="color: hsl(217, 10%, 50.8%)">
          Lorem ipsum dolor sit amet consectetur adipisicing elit.
          Eveniet, itaque accusantium odio, soluta, corrupti aliquam
          quibusdam tempora at cupiditate quis eum maiores libero
          veritatis? Dicta facilis sint aliquid ipsum atque?
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
                    <input type="text" id="form3Example1" class="form-control" v-model="user.firstName" required />
                    <label class="form-label" for="form3Example1">First name*</label>
                  </div>
                </div>
                <div class="col-md-6 mb-4">
                  <div class="form-outline">
                    <input type="text" id="form3Example2" class="form-control" v-model="user.lastName" />
                    <label class="form-label" for="form3Example2">Last name</label>
                  </div>
                </div>
              </div>

              <!-- Email input -->
              <div class="form-outline mb-4">
                <input type="email" id="emailAddress" class="form-control" v-model="user.emailId" required />
                <label class="form-label" for="emailAddress">Email Address*</label>
              </div>

              <!-- Phone Number input -->
              <div class="form-outline mb-4">
                <input type="tel" id="phoneNumber" class="form-control" v-model="user.phoneNumber" />
                <label class="form-label" for="phoneNumber">Phone Number</label>
              </div>

              <!-- Address input -->
              <div class="form-outline mb-4">
                <input type="text" id="address" class="form-control" v-model="user.address" />
                <label class="form-label" for="address">Address</label>
              </div>

              <!-- Username input -->
              <div class="form-outline mb-4">
                <input type="text" id="userName" class="form-control" v-model="user.userName" required />
                <label class="form-label" for="userName">Username*</label>
              </div>

              <!-- Password input -->
              <div class="form-outline mb-4">
                <input type="password" id="password" class="form-control" v-model="user.password" required />
                <label class="form-label" for="password">Password*</label>
              </div>

              <!-- Password input confirmation -->
              <div class="form-outline mb-4">
                <input type="password" id="passwordConfirm" class="form-control" required />
                <label class="form-label" for="passwordConfirm">Confirm Password*</label>
              </div>

              <p>
                By creating an account, you are agreeing to LuckyPaws'
                <a
                    href="/"
                    style="cursor: pointer; color: var(--default-text-color)"
                >Terms & Services</a
                >
              </p>

              <!-- Checkbox -->
<!--              <div class="form-check d-flex justify-content-center mb-4">-->
<!--                <input class="form-check-input me-2" type="checkbox" value="" id="form2Example33" checked />-->
<!--                <label class="form-check-label" for="form2Example33">-->
<!--                  Subscribe to our newsletter-->
<!--                </label>-->
<!--              </div>-->

              <!-- Submit button -->
              <button type="submit" class="btn btn-primary btn-block mb-4">
                Sign Up
              </button>

              <!-- Register buttons -->
              <div class="text-center">
                <p>or sign up with:</p>
                <button type="button" class="btn btn-link btn-floating mx-1">
                  <i class="fab fa-facebook-f"></i>
                </button>

                <button type="button" class="btn btn-link btn-floating mx-1">
                  <i class="fab fa-google"></i>
                </button>

                <button type="button" class="btn btn-link btn-floating mx-1">
                  <i class="fab fa-twitter"></i>
                </button>

                <button type="button" class="btn btn-link btn-floating mx-1">
                  <i class="fab fa-linkedin-in"></i>
                </button>
              </div>
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