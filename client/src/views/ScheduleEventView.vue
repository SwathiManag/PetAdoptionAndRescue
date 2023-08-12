<template>
  <div class="schedule-event-page" style="background-color: #f7f7f7">
    <h4>Schedule Event</h4>
    <br/>


    <section class="form container">
      <div v-if="!submitted">
        <form class="event-input-form" id="event_form" enctype="multipart/form-data" @submit.prevent="saveEventRequest">
          <div class="form-group row">
            <label for="eventDate" class="col-sm-2 col-form-label">Event Date:</label>
            <div class="col-sm-10">
              <Datepicker
                  v-model="events.eventDate"
                  required
                  :min-date="state.disabledDates"
                  :prevent-disable-date-selection="state.preventDisableDateSelection">
              </Datepicker>
            </div>
          </div>
          <div class="form-group row">
            <label for="eventName" class="col-sm-2 col-form-label">Event Title:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="eventName"
                  name="eventName"
                  required
                  placeholder="Event Name"
                  v-model="events.title"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="location" class="col-sm-2 col-form-label">Location:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="location"
                  placeholder="Location"
                  name="location"
                  required
                  v-model="events.eventLocation"
              />
            </div>
          </div>
          <div class="form-group row">
            <label for="description" class="col-sm-2 col-form-label">Description:</label>
            <div class="col-sm-10">
              <input
                  type="text"
                  class="form-control"
                  id="description"
                  placeholder="Description"
                  name="description"
                  required
                  v-model="events.eventDescription"
              />
            </div>
          </div>
          <br />
          <div class="form-group row">
            <div class="col-sm-10">
              <button type="submit" class="btn btn-dark p-3" value="Submit Request">Schedule Event</button>
            </div>
          </div>
        </form>
      </div>
      <div v-else class="alert alert-success" role="alert">
        <p>Event Scheduled successfully!</p>
      </div>
    </section>
  </div>
</template>


<script lang="ts">
import '@vuepic/vue-datepicker/dist/main.css'
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import EventDataService from "../services/EventDataService";
import ResponseData from "@/types/ResponseData";
import {useStorage} from "vue3-storage";
import Events from "@/types/Events";
import {defineComponent} from "vue";

export default defineComponent({
  name: "schedule-event-view",
  components: { Datepicker },
  data() {
    return {
      events: {
        id: null,
        title: "",
        eventDescription: "",
        eventDate: 0,
        eventLocation: "",
        user_id: 0,
        updated_user_id:0,
      } as Events,
      state : {
        disabledDates: new Date().toDateString(),
        preventDisableDateSelection: true
      },
      submitted: false,
    };
  },
  methods: {
    saveEventRequest() {
      const storage = useStorage();
      //alert(storage.getStorageSync("user_id"));
      let data = {
        title: this.events.title,
        eventDescription: this.events.eventDescription,
        eventDate: this.events.eventDate,
        eventLocation: this.events.eventLocation,
        user_id: storage.getStorageSync("user_id"),

      };
      EventDataService.create(data)
          .then((response: ResponseData) => {
            this.events.id = response.data.id;
            console.log(response.data);
            // alert("Posted successfully!!");
            this.submitted = true;
          })
          .catch((e: Error) => {
            console.log(e);
          });
    },
  },
});
</script>




<style scoped>
h4 {
  color: black;
  text-align: center;
}

div.schedule-event-page {
  margin: 30px;
}

</style>
