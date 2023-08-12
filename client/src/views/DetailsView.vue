<script lang="ts">
import PetDataService from "@/services/PetDataService";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from 'dayjs';
import Pet from "@/types/Pet";
import axios from "axios";
import {useStorage} from "vue3-storage";


export default defineComponent({
  name: "detail-view",
  data() {
    return {
      image: "",
      petList: [] as Pet[],
      baseURL:"",
      current_user_id: 0,
      isDisabled: false,
    };
  },
  mounted() {
    this.retrievePet(this.getId());
    this.setCurrentUserId(this.getId());
    this.retrieveImageList(this.getImageId());
  },
  methods: {
    retrievePet(id: any) {
      PetDataService.get(id)
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    getId(){
      return window.location.pathname.split('/')[2];
    },
    getImageId(){
      return window.location.pathname.split('/')[3];
    },
    setCurrentUserId(id: any) {
      const storage = useStorage();
      this.current_user_id = storage.getStorageSync('user_id');
    },
    disable() {
      this.isDisabled = true;
    },
    retrieveImageList(id: any) {
      this.baseURL= "https://luckypaws.discovery.cs.vt.edu/api";
      axios.get(this.baseURL+`/image/ID/`+id,{

        headers:{

          'content-type': 'image/png',
          'accept': 'image/png'
        },

        responseType: 'blob'

      }).then((res) => {

        const urlCreator = window.URL || window.webkitURL
        this.image = urlCreator.createObjectURL(res.data)

      }).catch((error) => {

        console.log(error)
      })
    },
    formatDate(dateString:any) {
      const date = dayjs(dateString);
      // Then specify how you want your dates to be formatted
      return date.format('MMMM D, YYYY');
    },
    approveAdoptionRequest(id: any, updated_user_id: any){
      // const storage = useStorage();
      // this.current_user_id = storage.getStorageSync('user_id');
      PetDataService.updateReqStatus(id, this.current_user_id,'Approved')
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
            alert("Pet adoption request approved!")
            this.$router.go(0)
            ;
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    rejectAdoptionRequest(id: any, updated_user_id: any){
      const storage = useStorage();
      this.current_user_id = storage.getStorageSync('user_id');
      PetDataService.updateReqStatus(id, this.current_user_id,'Rejected')
          .then((response: ResponseData) => {
            this.petList = response.data;
            console.log(response.data);
            alert("Pet adoption request rejected!")
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
  <div class="container" style="background-color: #f7f7f7">
    <h4>Pet Details</h4>
    <br/>
    <div class="row">
      <div class="col-lg-4">
        <div class="white-box text-center"><img v-bind:src="image" class="img-responsive"></div>
      </div>
      <div class="col-lg-7 col-md-7 col-sm-6 info" v-for="pet in petList" :key="pet.id">
        <section class="pet-details">
        <div class="form-group row" v-if="petList[0].pet_name.length > 0">
          <span class="col-sm-3">Name:</span>
          <div class="col-sm-9">
            <p>{{petList[0].pet_name}}</p>
          </div>
        </div>
        <div class="form-group row">
          <span class="col-sm-3">Animal Type:</span>
          <div class="col-sm-9">
            <p>{{petList[0].petType}}</p>
          </div>
        </div>
        <div class="form-group row" v-if="petList[0].breed.length > 0">
          <span class="col-sm-3">Breed:</span>
          <div class="col-sm-9">
            <p>{{petList[0].breed}}</p>
          </div>
        </div>
        <div class="form-group row" v-if="petList[0].age > 0">
          <span class="col-sm-3">Age:</span>
          <div class="col-sm-9">
            <p>{{petList[0].age}}</p>
          </div>
        </div>
        <div class="form-group row">
          <span class="col-sm-3">Color:</span>
          <div class="col-sm-9">
            <p>{{petList[0].color}}</p>
          </div>
        </div>
        <div class="form-group row">
          <span class="col-sm-3">Size:</span>
          <div class="col-sm-9">
            <p>{{petList[0].size}}</p>
          </div>
        </div>
        <div class="form-group row">
          <span class="col-sm-3">Location:</span>
          <div class="col-sm-9">
            <p>{{petList[0].location}}</p>
          </div>
        </div>
        <div class="form-group row">
          <span class="col-sm-3">Description:</span>
          <div class="col-sm-9">
            <p>{{petList[0].description}}</p>
          </div>
        </div>
          &nbsp;
        </section>
        <section class="approval-rejection-buttons" v-if="pet.reqFlag === 'Request Pending' && petList[0].userID === this.current_user_id">
          <button class="btn btn-success" v-bind:disabled="isDisabled"
                  @click="disable(); approveAdoptionRequest(petList[0].id)">Approve Request</button>
          &nbsp;
          <button class="btn btn-danger" v-bind:disabled="isDisabled"
                  @click="disable(); rejectAdoptionRequest(petList[0].id)">Reject Request</button>
        </section>
      </div>
    </div>
  </div>
</template>


<style scoped>
h3, h4, span {
  color: black;
}

span {
  font-weight: bold;
}

div.info {
  border: black dotted 2px;
  border-radius: 10px;
  margin-left: 20px;
  padding: 20px;
}

div.container{
  padding: 30px;
}
</style>
