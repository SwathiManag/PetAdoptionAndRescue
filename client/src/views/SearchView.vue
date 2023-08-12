<script lang="ts">
import PetDataService from "@/services/PetDataService";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from 'dayjs';
import Pet from "@/types/Pet";

export default defineComponent({
  name: "search-view",
  data() {
    return {
      petList: [] as Pet[],
    };
  },
  mounted() {
    this.searchPet(this.getKey());
  },
  methods: {
    searchPet(key: any){
      PetDataService.searchPetByName(key)
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    getKey(){
      return window.location.pathname.split('/')[2];
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
p {
  text-align: center;
}
</style>

<template>
  <div class="search-page" style="background-color: #f7f7f7">
    <div v-if="petList.length > 0">
      <h4>Search Results</h4>
      <div class="container-fluid">
        <div class="row">
          <div class="tableContainer col-xs-12 table-responsive">
            <table class="table table-hover">
              <thead class="table-dark">
              <tr>
                <th scope="col">Request ID</th>
                <th scope="col">Pet Type</th>
                <th scope="col">Created by</th>
                <th scope="col">Created date</th>
                <th scope="col">Pet Breed</th>
                <th scope="col">Request Type</th>
              </tr>
              </thead>
              <tbody>
              <template v-for="pet in petList" :key="pet.id">
                <tr>
                  <th class="reqId" scope="row"><router-link
                      :to="'../details/'+ pet[0]+'/'+pet[10]"
                  >
                    {{pet[0]}}
                  </router-link></th>
                  <td>{{pet[2]}}</td>
                  <td>{{pet[7]}}</td>
                  <td>{{formatDate(pet[8])}}</td>
                  <td>{{pet[3]}}</td>
                  <td>{{pet[11]}}</td>
                </tr>
              </template>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
    <div v-else>
      <p class="alert alert-primary">
        We couldn't find a match for "{{this.getKey()}}". Please try another search.
      </p>
    </div>
  </div>
</template>
