<script lang="ts">
import PetDataService from "@/services/PetDataService";
import Pet from "@/types/Pet";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from "dayjs";
import UploadFilesServices from "@/services/UploadFilesServices";
import {useStorage} from "vue3-storage";
export default defineComponent({
  name: "adoption-listing",
  data() {
    return {
      petList: [] as Pet[],
      url: "" as string,
      current_user_id: 0,
    };
  },
  mounted() {
    this.retrievePetList();
  },
  methods: {
    retrievePetList() {
      PetDataService.getbyFlagType('Adoption')
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    retrievePet(id: any) {
      PetDataService.get(id)
          .then((response: ResponseData) => {
            this.url = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
      const inputs = document.getElementById("adoption_form")!.getElementsByTagName('input');
      for (let i = 0, len = inputs.length; i < len; ++i) {
        inputs[i].readOnly = true;
      }
    },
    formatDate(dateString:any) {
      const date = dayjs(dateString);
      // Then specify how you want your dates to be formatted
      return date.format('MMMM D, YYYY');
    },

    requestAdoption(id: any, updated_user_id: any){
      const storage = useStorage();
      this.current_user_id = storage.getStorageSync('user_id');
      PetDataService.updateReqStatus(id, this.current_user_id,'Request Pending')
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
            alert("Pet requested for Adoption!")
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
  <h4>Adoption Requests</h4>
  <div class="container-fluid" v-if="petList.length > 0">
    <div class="row">
      <div class="tableContainer col-xs-12 table-responsive">
        <table class="table table-hover">
          <thead class="table-dark">
          <tr>
            <th scope="col">Post ID</th>
            <th scope="col">Name</th>
            <th scope="col">Age</th>
            <th scope="col">Type</th>
            <th scope="col">Breed</th>
            <th scope="col">Posted By</th>
            <th scope="col">Created Date</th>
            <th scope="col">Make Request</th>
            <th scope="col">Request Status</th>
          </tr>
          </thead>
          <tbody>
          <template v-for="pet in petList" :key="pet.id">
            <tr>
              <th class="reqId" scope="row"><router-link
                  :to="'../details/' + pet[0]+'/'+pet[10]"
              >{{pet[0]}}
              </router-link>
              </th>
              <td>{{pet[1]}}</td>
              <td>{{pet[4]}}</td>
              <td>{{pet[2]}}</td>
              <td>{{pet[3]}}</td>
              <td>{{pet[7]}}</td>
              <td>{{formatDate(pet.created_date)}}</td>
              <td>
                <button class=" btn btn-success" v-if="pet[9] ==='Created' && $storage.getStorageSync('user_id') !== pet[11]"  @click="requestAdoption(pet[0])">Request Adoption</button>
                <button class=" btn btn-success" v-else disabled>Request Adoption</button>
              </td>
              <td>{{pet[9]}}</td>
            </tr>
          </template>
          </tbody>
        </table>
      </div>
    </div>
  </div>
  <div v-else>
    <p class="alert alert-primary">
     No Requests at this moment!
    </p>
  </div>
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
.alert-primary{
  text-align: center;
}
</style>

