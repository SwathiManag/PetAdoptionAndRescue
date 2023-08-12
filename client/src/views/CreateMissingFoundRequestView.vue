<style scoped>
.create-missing-found-request-page{
margin: 30px;
}
#petImage {
  width: 230px;
}
h4 {
  color: black;
  text-align: center;
}
</style>


<template>
<div class="create-missing-found-request-page" style="background-color: #f7f7f7">
  <h4>Create Missing/Found Request</h4>
  <br />
  <br />
  <section class="form container">
      <div v-if="!submitted">
        <form class="missing-found-request-input-form" id="missing_found_form" enctype="multipart/form-data" @submit.prevent="saveRequest">
          <fieldset class="form-group">
            <div class="row">
              <label for="requestType" class="col-sm-2 col-form-label">Request Type:</label>
              <div class="col-sm-10">
                <div class="form-check" aria-required="true">
                  <input
                      class="form-check-input"
                      type="radio"
                      name="RequestType"
                      required
                      value="Missing"
                      v-model="pet.flag"
                  />
                  <label class="form-check-label" for="missingRequest">
                    Missing Request
                  </label>
                </div>
                <div class="form-check">
                  <input
                      class="form-check-input"
                      type="radio"
                      name="RequestType"
                      value="Found"
                      v-model="pet.flag"
                  />
                  <label class="form-check-label" for="foundRequest">
                    Found Request
                  </label>
                </div>
              </div>
            </div>
          </fieldset>
          <br />
          <div class="form-group row" v-if="pet.flag === 'Missing'">
            <label for="petName" class="col-sm-2 col-form-label">Pet Name:*</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="petName"
                  name="petName"
                  required
                  placeholder="Pet Name"
                  v-model="pet.pet_name"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="petType" class="col-sm-2 col-form-label">Pet Type:*</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="petType"
                  name="petType"
                  required
                  placeholder="Pet Type"
                  v-model="pet.petType"
              />
            </div>
          </div>
          <div class="form-group row" v-if="pet.flag === 'Missing'">
            <label for="breed" class="col-sm-2 col-form-label">Breed:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="breed"
                  placeholder="Breed"
                  name="breed"
                  v-model="pet.breed"
              />
            </div>
          </div>
          <div class="form-group row" v-if="pet.flag === 'Missing'">
            <label for="age" class="col-sm-2 col-form-label">Age:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="age"
                  placeholder="Age"
                  name="age"
                  v-model="pet.age"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="color" class="col-sm-2 col-form-label">Color:*</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="color"
                  placeholder="Color"
                  name="color"
                  required
                  v-model="pet.color"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="size" class="col-sm-2 col-form-label">Size:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="size"
                  placeholder="Size"
                  name="size"
                  v-model="pet.size"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="description" class="col-sm-2 col-form-label">Description:*</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="description"
                  placeholder="Description"
                  name="description"
                  required
                  v-model="pet.description"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="location" class="col-sm-2 col-form-label">Location:*</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="location"
                  placeholder="Location"
                  name="location"
                  required
                  v-model="pet.location"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="petImage" class="col-sm-2 col-form-label">Upload Pet Image:*</label>
            <div class="col-sm-10">
              <input
                  type="file"
                  class="form-control"
                  id="petImage"
                  name="petImage"
                  placeholder="Color"
                  required
                  @change="onFileSelected"
              />
              <br />
              <input type="text" id="image" name="image" hidden />
            </div>
          </div>
          <br />
          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-dark p-3" value="Submit Request">Submit Request</button>
            </div>
          </div>
        </form>
      </div>
      <div v-else class="alert alert-success" role="alert">
        <p>{{this.pet.flag}} Request posted successfully!</p>
      </div>
    </section>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import PetDataService from "../services/PetDataService";
import Pet from "../types/Pet";
import ResponseData from "@/types/ResponseData";
import UploadFilesServices from "../services/UploadFilesServices";
import {useStorage} from "vue3-storage";


export default defineComponent({
  name: "create-missing-found-request-view",
  data() {
    return {
      pet: {
        id: null,
        pet_name: "",
        petType: "",
        location: "",
        breed: "",
        age: 0,
        description: "",
        size: 0,
        color: "",
        flag: "",
      } as Pet,
      submitted: false,
      image: null,
    };
  },
  methods: {
    saveRequest() {
      const storage = useStorage();

      console.log(this.image);
      let data = {
        petType: this.pet.petType,
        location: this.pet.location,
        description: this.pet.description,
        size: this.pet.size,
        color: this.pet.color,
        flag: this.pet.flag,
        imagedata: this.image,
        pet_name:this.pet.pet_name,
        breed:this.pet.breed,
        age:this.pet.age,
        user_id: storage.getStorageSync("user_id"),

      };

      UploadFilesServices.upload(data)
          .then((response: ResponseData) => {
            this.pet.id = response.data.id;
            console.log(response.data);
            // alert("Posted successfully!!");
            this.submitted = true;
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
    onFileSelected(event: { target: { files: null[] } }) {
      console.log(event);
      this.image = event.target.files[0];
    },

    newRequest() {
      this.submitted = false;
      this.pet = {} as Pet;
    },
  },
});
</script>