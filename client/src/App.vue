<script setup lang="ts">
import { provide } from "vue";
import { ServiceItem } from "@/types";
import AppHeader from "@/components/AppHeader.vue";
import AppFooter from "@/../../client/src/components/AppFooter";
const serviceList: ServiceItem[] = [
  { serviceId: 1001, name: "Adoption" },
  { serviceId: 1002, name: "Lost/Found" },
  { serviceId: 1003, name: "Re-home" },
  { serviceId: 1004, name: "Animal in distress" },
];
provide("serviceList", serviceList);
</script>

<style scoped>
div {
  background-size: cover;
  background-position: center;
  height: auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  max-width: 100%;
}
</style>

<template>
  <div id="app">
    <app-header></app-header>
    <router-view></router-view>
    <app-footer class="footer"></app-footer>
  </div>
</template>

<style>
.footer {
  position: relative;
}
</style>
