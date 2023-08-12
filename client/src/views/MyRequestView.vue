<script lang="ts">
import PetDataService from "@/services/PetDataService";
import Pet from "@/types/Pet";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from 'dayjs';
import {useStorage} from "vue3-storage";

export default defineComponent({
  name: "rescue-listing",
  data() {
    return {
      petList: [] as Pet[],
      url: "" as string,
      user_id: 0,
    };
  },
  mounted() {
    this.retrievePetList();
  },
  methods: {
    retrievePetList() {
      const storage = useStorage();
      this.user_id = storage.getStorageSync('user_id');
      PetDataService.getbyUserId(this.user_id)
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
      const inputs = document.getElementById("rescue_form")!.getElementsByTagName('input');
      for (let i = 0, len = inputs.length; i < len; ++i) {
        inputs[i].readOnly = true;
      }
    },
    formatDate(dateString:any) {
      const date = dayjs(dateString);
      // Then specify how you want your dates to be formatted
      return date.format('MMMM D, YYYY');
    }
  },
})
</script>
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
<template>
  <section style="background-color: #f7f7f7">
    <h4>My Requests</h4>
    <div class="container-fluid" v-if="petList.length > 0">
      <div class="row">
        <div class="tableContainer col-xs-12 table-responsive">
          <table class="table table-hover">
            <thead class="table-dark">
            <tr>
              <th scope="col">Request ID</th>
              <th scope="col">Pet type</th>
              <th scope="col">Request Type</th>
              <th scope="col">Created date</th>
              <th scope="col">Description</th>
              <th scope="col">Request status</th>
            </tr>
            </thead>
            <tbody>
            <template v-for="pet in petList" :key="pet.id">
              <tr>
                <th class="reqId" scope="row"><router-link
                    :to="'../details/'+ pet.id+'/'+pet.petImage"
                >
                  {{pet.id}}
                </router-link></th>
                <td>{{pet.petType}}</td>
                <td>{{pet.flag}}</td>
                <td>{{formatDate(pet.created_date)}}</td>
                <td>{{pet.description}}</td>
                <td>{{pet.reqFlag}}</td>
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
  </section>
</template>