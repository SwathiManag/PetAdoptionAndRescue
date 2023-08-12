<template xmlns="http://www.w3.org/1999/html">
  <div class="user-profile-page">
    <section style="background-color: #f7f7f7;">
      <div class="container py-5">
        <div class="row">
          <div class="col-lg-4">
            <div class="card mb-4">
              <div class="card-body text-center" style="color: black" v-for="user in userList" :key="user.id">
                <!--                <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"-->
                <!--                     class="rounded-circle img-fluid" style="width: 150px;">-->
                <i class="fa-solid fa-6x fa-fw fa-circle-user" style="width: 150px;"></i>
                <h2 class="my-3">{{userList[0].firstName}} {{userList[0].lastName}}</h2>
                <!--                <p class="text-muted mb-1">Full Stack Developer</p>-->
                <!--                <p class="text-muted mb-4">Bay Area, San Francisco, CA</p>-->
                <div class="d-flex justify-content-center mb-2">
                  <!--                  <button type="button" class="btn btn-primary">Follow</button>-->
                  <!--                  <button type="button" class="btn btn-outline-primary ms-1">Message</button>-->
<!--                  <i class="far fa-edit"></i>-->
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-8">
            <div class="card mb-4" v-for="user in userList" :key="user.id">
              <div class="card-body" >
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">First Name</p>
                  </div>
                  <div class="col-sm-9">
                    <input type="text"  :disabled="isDisabled" v-model="userList[0].firstName">
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Last Name</p>
                  </div>
                  <div class="col-sm-9">
                    <input type="text" :disabled="isDisabled" v-model=userList[0].lastName>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Email Address</p>
                  </div>
                  <div class="col-sm-9">
                    <input type="text" :disabled="isDisabled" v-model=userList[0].emailId>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Phone Number</p>
                  </div>
                  <div class="col-sm-9">
                    <input type="text" :disabled="isDisabled" v-model=userList[0].phoneNumber>
                  </div>
                </div>
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <p class="mb-0">Address</p>
                  </div>
                  <div class="col-sm-9">
                    <input type="text" :disabled="isDisabled" v-model=userList[0].address>
                  </div>
                </div>
<!--                <hr>-->
<!--                <div class="row">-->
<!--                  <div class="col-sm-3">-->
<!--                    <p class="mb-0">Password</p>-->
<!--                  </div>-->
<!--                  <div class="col-sm-9">-->
<!--                    <input type="password" :disabled="isDisabled" v-model=userList[0].password>-->
<!--                  </div>-->
<!--                </div>-->
                <hr>
                <div class="row">
                  <div class="col-sm-3">
                    <button class="btn btn-success" @click="isDisabled = !isDisabled">Edit</button>
                  </div>
                  <div class="col-sm-9">
                    <button class="btn btn-success" :disabled="isDisabled" @click="saveUser()">Save</button>
                  </div>

                </div>
              </div>
            </div>

<!--            <div class="row">-->
<!--              <div class="col-md-6">-->
<!--                <div class="card mb-4 mb-md-0">-->
<!--                  <div class="card-body">-->
<!--                    <p class="mb-4">Participating Events-->
<!--                    </p>-->
<!--                    <p class="mb-1" style="font-size: .77rem;">FIRST EVENT</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 80%" aria-valuenow="80"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                    <p class="mt-4 mb-1" style="font-size: .77rem;">SECOND EVENT</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 72%" aria-valuenow="72"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                    <p class="mt-4 mb-1" style="font-size: .77rem;">THIRD EVENT</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 89%" aria-valuenow="89"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                    <p class="mt-4 mb-1" style="font-size: .77rem;">FOURTH EVENT</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 55%" aria-valuenow="55"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                  </div>-->
<!--                </div>-->
<!--              </div>-->
<!--              <div class="col-md-6">-->
<!--                <div class="card mb-4 mb-md-0">-->
<!--                  <div class="card-body">-->
<!--                    <p class="mb-4">Recent Posts-->
<!--                    </p>-->
<!--                    <p class="mb-1" style="font-size: .77rem;">POST 1</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 80%" aria-valuenow="80"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                    <p class="mt-4 mb-1" style="font-size: .77rem;">POST 2</p>-->
<!--&lt;!&ndash;                    <div class="progress rounded" style="height: 5px;">&ndash;&gt;-->
<!--&lt;!&ndash;                      <div class="progress-bar" role="progressbar" style="width: 72%" aria-valuenow="72"&ndash;&gt;-->
<!--&lt;!&ndash;                           aria-valuemin="0" aria-valuemax="100"></div>&ndash;&gt;-->
<!--&lt;!&ndash;                    </div>&ndash;&gt;-->
<!--                  </div>-->
<!--                </div>-->
<!--              </div>-->
<!--            </div>-->
          </div>
        </div>
      </div>
    </section>
    <div v-if="successful" class="alert alert-success">
      <h4>User Details Saved Successfully!</h4>
<!--      <h4><a href="/sign-in" class="alert-link">Click to Continue</a></h4>-->
    </div>
  </div>
</template>

<script lang="ts">
import UserDataService from "@/services/UserDataService";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import User from "@/types/User";
import {useStorage} from "vue3-storage";


export default defineComponent({
  name: "detail-view",
  data() {
    return {
      user: {
        id: null,
        firstName: "",
        lastName: "",
        address: "",
        emailId: "",
        phoneNumber: 0,
      } as User,
      userList: [] as User[],
      baseURL:"",
      isDisabled: true,
      successful: false,
      message: "",

    };
  },
  mounted() {
    this.retrieveUser(this.getId());
  },
  methods: {
    retrieveUser(id: any) {
      UserDataService.get(id)
          .then((response: ResponseData) => {
            this.userList = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },

getId(){
  const storage = useStorage();
  return storage.getStorageSync("user_id");
},
    saveUser() {
      const storage = useStorage();

      let data = {
        id: this.getId(),
        emailId: this.userList[0].emailId,
        firstName: this.userList[0].firstName,
        lastName: this.userList[0].lastName,
        phoneNumber: this.userList[0].phoneNumber,
        address: this.userList[0].address,
        successful: this.successful = false,
        message: this.message = "",
        // password: this.userList[0].password,
      };

      UserDataService.update(this.getId(),data)
          .then((response: ResponseData) => {
            this.user.id = response.data.id;
            console.log(response.data);
            //this.submitted = true;
            this.successful = true;
            //this.$router.push({name: 'sign-in-view'})

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
})
</script>


<style scoped>

</style>
