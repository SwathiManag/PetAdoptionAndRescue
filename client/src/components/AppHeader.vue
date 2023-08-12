<style scoped>
.dropdown-menu li {
  position: relative;
}
.dropdown-menu .dropdown-submenu {
  display: none;
  position: absolute;
  left: 100%;
  top: -7px;
}
.dropdown-menu .dropdown-submenu-left {
  right: 100%;
  left: auto;
}
.dropdown-menu > li:hover > .dropdown-submenu {
  display: block;
}
</style>

<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand text-white" style="margin-left: 50px" href="/"
        ><i class="fa-solid fa-paw"></i>LuckyPaws</a
      >
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a
              class="nav-link active"
              aria-current="page"
              href="/"
              style="margin-left: 50px"
              >Home</a
            >
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdown"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Services
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li>
                <a class="dropdown-item" href="#">
                  Pet Adoption &raquo;
                </a>
                <ul class="dropdown-menu dropdown-submenu">
                  <li v-if="$storage.getStorageSync('role') === 'user'">
                    <a class="dropdown-item" href="/post-for-adoption">Post Your Pet for Adoption</a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="/adoption-listing">View all Adoption Requests</a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  Pet Rescue &raquo;
                </a>
                <ul class="dropdown-menu dropdown-submenu">
                  <li v-if="$storage.getStorageSync('role') === 'user'">
                    <a class="dropdown-item" href="/create-rescue-request">Post a Rescue Request</a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="/rescue-requests-listing">View all Rescue Request</a>
                  </li>
                </ul>
              </li>
              <li>
                <a class="dropdown-item" href="#">
                  Missing/Found Pet &raquo;
                </a>
                <ul class="dropdown-menu dropdown-submenu">
                  <li v-if="$storage.getStorageSync('role') === 'user'">
                    <a class="dropdown-item" href="/create-missing-found-request">Post a Missing/Found Request</a>
                  </li>
                  <li>
                    <a class="dropdown-item" href="/missing-found-requests-listing">View all Missing/Found Requests</a>
                  </li>
                </ul>
              </li>
              <li><hr class="dropdown-divider" /></li>
              <li>
                <a class="dropdown-item text-dark" href="/my-request"
                  >My Requests</a
                >
              </li>
              <li v-if="$storage.getStorageSync('role') === 'admin'">
                <a class="dropdown-item text-dark" href="/user-management"
                >Manage Users</a
                >
              </li>

            </ul>
          </li>
          <li class="nav-item dropdown">
            <a
                class="nav-link dropdown-toggle"
                href="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
            >Events
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li v-if="($storage.getStorageSync('role') === 'user')"><a class="dropdown-item" href="/schedule-event">Schedule Event</a></li>
              <li><a class="dropdown-item" href="/events-listing">View All Events</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/contact">Contact</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" href="/tips">Tips</a>
          </li>
        </ul>
        <form class="d-flex">
          <input
            class="form-control me-2"
            type="search"
            placeholder="Search"
            aria-label="Search"
            id="search"
            v-model="searchKey"
          />
        <router-link :to="'/search/'+ searchKey"><button class="btn btn-outline-light" type="submit"><i class="fa-solid fa-magnifying-glass"></i></button></router-link>
        </form>
        <div class="dropdown" style="margin-left: 30px; margin-right: 50px">
          <button class="btn btn-outline-light" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
            <i class="fa-solid fa-user"></i> Account
          </button>
          <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
            <li v-if="($storage.getStorageSync('role') !== 'user') && ($storage.getStorageSync('role') !== 'admin')">
              <a class="dropdown-item" href="/sign-in">Sign In</a></li>
            <li v-if="$storage.getStorageSync('role') === 'user' || $storage.getStorageSync('role') === 'admin'">
              <a class="dropdown-item" href="/user-profile">View Profile</a></li>
            <li><a class="dropdown-item" href="#" @click="signOut">Sign Out</a></li>
          </ul>
        </div>
      </div>
    </div>
  </nav>
  <div v-if="signOutFlag"  class="alert alert-success">
    <h4>Logged out Successfully!</h4>
    <h4><a href="/sign-in" class="alert-link">Click to Login</a></h4>
  </div>
</template>

<script lang="ts">
import {defineComponent} from "vue";
import { useStorage } from "vue3-storage";
import Pet from "@/types/Pet";
export default defineComponent({

  name: "sign-in",
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
    signOutFlag: false,
      user_id:0,
      role: "",
      searchKey: ''
  };
  },

  mounted() {
    this.getRole();
  },

  methods: {
    signOut() {
            const storage = useStorage();
            if(storage.getStorageSync("user_id")==null || storage.getStorageSync("user_id") == 'undefined'){
              alert("You are not logged in. Please login to Continue");
            }
            else {
              storage.clearStorageSync();
              this.signOutFlag = true;
            }

    },
    getRole() {
      const storage = useStorage();
      //alert(storage.getStorageSync("role"));
      return this.role = storage.getStorageSync("role");
    }
     },
});



</script>
