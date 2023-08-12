<script lang="ts">
import PetDataService from "@/services/PetDataService";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from "dayjs";
import {useStorage} from "vue3-storage";
import User from "@/types/User";
import UserDataService from "@/services/UserDataService";
export default defineComponent({
  name: "user-management",
  data() {
    return {
      userList: [] as User[],
      url: "" as string,
      current_user_id: 0,
    };
  },
  mounted() {
    this.retrieveUserList();
  },
  methods: {
    retrieveUserList() {
      UserDataService.getAll()
          .then((response: ResponseData) => {
            this.userList = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    retrieveUser(id: any) {
      UserDataService.get(id)
          .then((response: ResponseData) => {
            this.url = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    formatDate(dateString:any) {
      const date = dayjs(dateString);
      // Then specify how you want your dates to be formatted
      return date.format('MMMM D, YYYY');
    },

    //CODE NOT FUNCTIONING YET
    removeUser(id: any){
      UserDataService.delete(id)
          .then((response: ResponseData) => {
            this.userList = response.data;
            console.log(response.data);
            alert("User has been deleted")
            this.$router.go(0)
            ;
          })
          .catch((e: Error) => {
            console.log(e);
          });
    }
  },
})
</script>

<template>
  <section style="background-color: #f7f7f7">
    <h4>Manage Users</h4>
    <div class="container-fluid">
      <div class="row">
        <div class="tableContainer col-xs-12 table-responsive">
          <table class="table table-hover">
            <thead class="table-dark">
            <tr>
              <th scope="col">User ID</th>
              <th scope="col">Role</th>
              <th scope="col">Username</th>
              <th scope="col">First Name</th>
              <th scope="col">Last Name</th>
              <th scope="col">Email Address</th>
              <th scope="col">Phone Number</th>
              <th scope="col">Address</th>
              <th scope="col">Created Date</th>
              <th scope="col">Remove User</th>
            </tr>
            </thead>
            <tbody>
            <template v-for="user in userList" :key="user.id">
              <tr>
                <th class="reqId" scope="row">{{user.user_id}}</th>
                <td>{{user.role}}</td>
                <td>{{user.userName}}</td>
                <td>{{user.firstName}}</td>
                <td>{{user.lastName}}</td>
                <td>{{user.emailId}}</td>
                <td>{{user.phoneNumber}}</td>
                <td>{{user.address}}</td>
                <td>{{formatDate(user.createdDate)}}</td>

                <td>
                  <button class=" btn btn-success" @click="removeUser(user.user_id)">Remove</button>
                </td>
              </tr>
            </template>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </section>
</template>


<style scoped>
h4 {
  color: black;
  margin: 1em;
}

.row {
  margin-right: 1em;
  margin-left: 1em;
  margin-bottom: 1em;
  font-size: medium;

}
td{
  background-color: #fffdf3;
  color: black;
}

.reqId{
  background-color: #fffdf3;
  color: black;
}
</style>
