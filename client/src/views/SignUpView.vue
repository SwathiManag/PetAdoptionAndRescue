<template>
  <sign-up></sign-up>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import UserDataService from "../services/UserDataService";
import User from "../types/User";
import ResponseData from "@/types/ResponseData";
import SignUp from "@/components/SignUp.vue";

export default defineComponent({
  name: "sign-up-user",
  components: { SignUp },
  data() {
    return {
      user: {
        id: null,
        firstName: "",
        password: "",
      } as User,
      submitted: false,
    };
  },
  methods: {
  },
});
</script>

<style scoped>
.sign-up-page {
  display: flex;
  flex-direction: row;
  justify-content: center;
}

h3 {
  margin-top: 20px;
  margin-left: 15%;
  margin-right: 15%;
}

form {
  /*border: 2px solid;*/
  margin-left: 15%;
  margin-right: 15%;
  width: 70%;
}

.sign-up-form-input {
  padding-top: 30px;
}

.sign-up-form-input > section {
  overflow: visible;
  padding-right: 20px;
  padding-left: 20px;
}

p {
  font-size: small;
  margin-top: 5px;
  margin-left: 5px;
}

a {
  font-size: small;
}
</style>
