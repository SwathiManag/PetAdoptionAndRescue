<script setup lang="ts"></script>

<style scoped>
.home-page {
  display: flex;
  flex-direction: row;
  align-content: space-evenly;
}

.welcome-text {
  margin-top: 50px;
  margin-left: 150px;
  width: 35em;
}

.cat-image {
  margin-right: 500px;
  margin-bottom: 50px;
  margin-top: 50px;
  border-radius: 20px;
}

.dog-image {
  margin-right: 120px;
  margin-bottom: 20px;
  margin-top: 50px;
  border-radius: 20px;
}
</style>
<template>
  <div class="home-page" style="background-color: #f7f7f7">
    <br />
    <section class="welcome-text flow-content container">
      <h3 class="display-3 text-dark">
        Adopt or Rescue your lifetime love today!<br>
      </h3>
      <small class="text-dark">
        Please don’t shop, adopt instead!<br>
      </small>
      <p>
        You can make a difference!<br>
      </p>
      <a href="/adoption-listing">
        <button class="btn btn-dark btn-lg p-3">Adopt Now</button>
      </a>

    </section>
    <section class="animal-images">
      <img class="cat-image"
        src="@/assets/images/site/home_page_pets.png"
        alt="animals-home-page"
      />
    </section>
  </div>
</template>
