<script lang="ts">
import EventDataService from "@/services/EventDataService";
import Events from "@/types/Events";
import {defineComponent} from "vue";
import ResponseData from "@/types/ResponseData";
import dayjs from "dayjs";
import {useStorage} from "vue3-storage";
import PetDataService from "@/services/PetDataService";
import EventParticipants from "@/types/EventParticipants";
export default defineComponent({
  name: "events-listing-view",
  data() {
    return {
      eventParticipants:{
        id:null,
        event_id:0,
        user_id:0,
      } as EventParticipants,
      eventsList: [] as Events[],
      url: "" as string,
      current_user_id: 0,
      submitted:false,
    };
  },
  mounted() {
    this.retrieveEventsList();
  },
  methods: {
    retrieveEventsList() {
      EventDataService.getAll()
          .then((response: ResponseData) => {
            this.eventsList = response.data;
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

    formatTime(dateString:any) {
      const date = dayjs(dateString);
      // Then specify how you want your dates to be formatted
      return date.format('H:mm');
    },

    participateEvent(id: any){
      const storage = useStorage();
      this.current_user_id = storage.getStorageSync('user_id');
      this.eventParticipants.event_id = id;

      let data = {
        event_id: this.eventParticipants.event_id,
        user_id: this.current_user_id,
      };
      EventDataService.participateEvent(data)
          .then((response: ResponseData) => {
            this.eventsList = response.data;
            console.log(response.data);
            this.submitted = true;
            //alert("Thank you for participation!")
           // this.$router.go(0)
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
    <h4>Events</h4>
    <div class="container-fluid" v-if="!submitted && eventsList.length > 0">
      <div class="row">
        <div class="tableContainer col-xs-12 table-responsive">
          <table class="table table-hover">
            <thead class="table-dark">
            <tr>
              <th scope="col">Event Date</th>
              <th scope="col">Event Time</th>
              <th scope="col">Event Title</th>
              <th scope="col">Location</th>
              <th scope="col">Description</th>
              <th scope="col">Register to Participate</th>
            </tr>
            </thead>
            <tbody>
            <template v-for="event in eventsList" :key="event.event_id">
              <tr>
                <td>{{formatDate(event.eventDate)}}</td>
                <td>{{formatTime(event.eventDate)}}</td>
                <td>{{event.title}}</td>
                <td>{{event.eventLocation}}</td>
                <td>{{event.eventDescription}}</td>
                <td>
                  <button class=" btn btn-success" @click="participateEvent(event.event_id)">Participate</button>
                </td>
              </tr>
            </template>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <div v-if="submitted" class="alert alert-success">
      <p>Thank you for your participation!</p>
    </div>
    <div v-if="eventsList.length===0" >
      <p class="alert alert-primary">
        No Events scheduled at this time!
      </p>
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

.alert-primary{
  text-align: center;
}
</style>
