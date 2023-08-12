<script setup lang="ts">
import { inject } from "vue";
const serviceList = inject("serviceList");
</script>

<style scoped>
.header-dropdown {
  position: relative;
}

.services-button {
  color: #3f301d;
  background-color: #e3cd81;
  margin-top: 0.25em;
}

.header-dropdown ul {
  background-color: #fffdf3;
  color: black;
  display: none;
}

.header-dropdown li {
  padding: 0.25em 0.5em;
}

.header-dropdown a {
  color: black;
  text-decoration: none;
}

.header-dropdown li:hover {
  background: #e3cd81;
}

.header-dropdown:hover ul {
  display: block;
  position: absolute;
  min-width: 8em;
  z-index: 1;
  cursor: pointer;
}

.dropdown-content {
  position: absolute;
  text-align: left;
}
</style>

<template>
  <div class="header-dropdown">
    <button
      class="button services-button"
      style="border-radius: 12px; font-size: large; box-shadow: none"
    >
      <i class="fa-solid fa-caret-down"></i>
      Services
    </button>
    <div class="dropdown-content">
      <ul>
        <li
          v-for="service in serviceList"
          :key="service.serviceId"
          style="font-size: large"
        >
          <router-link :to="'../services/' + service.name">
            {{ service.name }}
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</template>
