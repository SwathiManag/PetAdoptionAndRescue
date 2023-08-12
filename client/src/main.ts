import "bootstrap/dist/css/bootstrap.css";
import { createApp } from "vue";
import "@/assets/css/global.css"; // imports the global CSS before all other CSS files
import App from "./App.vue";
import router from "./router/router";
import Vue3Storage, {StorageType} from "vue3-storage";
createApp(App).use(router).use(Vue3Storage, { namespace: "pro_", storage: StorageType.Local })
    .mount("#app");

import "bootstrap/dist/js/bootstrap.js";
