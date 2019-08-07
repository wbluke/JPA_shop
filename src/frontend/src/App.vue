<template>
  <v-app>
	  <header-bar></header-bar>

  </v-app>
</template>

<script>
import HeaderBar from './components/HeaderBar';

export default {
  name: 'App',
  components: {
    HeaderBar
  },
  data: () => ({
    //
  }),
};
</script>
