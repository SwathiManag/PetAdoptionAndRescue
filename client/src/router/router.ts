import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import HomeView from "../views/HomeView.vue";
import SignUpView from "../views/SignUpView.vue";
import SignInView from "../views/SignInView.vue"
import UserProfileView from "../views/UserProfileView.vue";
import PostForAdoptionView from "../views/PostForAdoptionView.vue";
import CreateRescueRequestView from "../views/CreateRescueRequestView.vue"
import RescueRequestsListingView from "../views/RescueRequestsListingView.vue"
import AboutView from "../views/AboutView.vue"
import AdoptionRequestListingView from "../views/AdoptionRequestListingView.vue"
import CreateMissingFoundRequestView from "../views/CreateMissingFoundRequestView.vue"
import MissingFoundRequestsListingView from "../views/MissingFoundRequestsListingView.vue"
import DetailsView from "../views/DetailsView.vue";
import PrivacyTermsView from "@/views/PrivacyTermsView.vue";
import ContactView from "@/views/ContactView.vue";
import MyRequestView from "@/views/MyRequestView.vue";
import SearchView from "@/views/SearchView.vue";
import UpdatePasswordView from "@/views/UpdatePasswordView.vue";
import ScheduleEventView from "@/views/ScheduleEventView.vue";
import EventsListingView from "@/views/EventsListingView.vue";
import TipsView from "@/views/TipsView.vue";
import UserManagementView from "@/views/UserManagementView.vue";


const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "home-view",
    component: HomeView,
  },
  {
    path: "/sign-up",
    name: "sign-up-view",
    component: SignUpView,
  },
  {
    path: "/user-profile",
    name: "user-profile-view",
    component: UserProfileView,
  },
  {
    path: "/post-for-adoption",
    name: "post-for-adoption-view",
    component: PostForAdoptionView,
  },
  {
    path: "/create-rescue-request",
    name: "create-rescue-request-view",
    component: CreateRescueRequestView,
  },
  {
    path: "/rescue-requests-listing",
    name: "rescue-requests-listing-view",
    component: RescueRequestsListingView,
  },
  {
    path: "/create-missing-found-request",
    name: "create-missing-found-request-view",
    component: CreateMissingFoundRequestView,
  },
  {
    path: "/missing-found-requests-listing",
    name: "missing-found-requests-listing-view",
    component: MissingFoundRequestsListingView,
  },
  {
    path: "/sign-in",
    name: "sign-in-view",
    component: SignInView,
  },
  {
    path: "/about",
    name: "about-view",
    component: AboutView,
  },
  {
    path: "/privacy-terms",
    name: "privacy-terms-view",
    component: PrivacyTermsView,
  },
  {
    path: "/contact",
    name: "contact-view",
    component: ContactView,
  },
  {
    path: "/tips",
    name: "tips-view",
    component: TipsView,
  },
  {
    path: "/adoption-listing",
    name: "adoption-listing",
    component: AdoptionRequestListingView,
  },
  {
    path: "/details/:id/:imageId",
    name: "detail-view",
    component: DetailsView,
    props: true
  },
  {
    path: "/search/:key",
    name: "search",
    component: SearchView,
    props: true
  },
  {
    path: "/my-request",
    name: "my-request",
    component: MyRequestView,
  },
  {
    path: "/events-listing",
    name: "events-listing-view",
    component: EventsListingView,
  },
  {
    path: "/update-password",
    name: "update-password",
    component: UpdatePasswordView,
  },
  {
    path: "/schedule-event",
    name: "schedule-event-view",
    component: ScheduleEventView,
  },

  {
    path: "/user-management",
    name: "user-management",
    component: UserManagementView,
  },

];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});
export default router;
