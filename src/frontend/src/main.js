import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'

// TODO : install axios >>> npm install --save axios
// import axios from 'axios'

// Vue.prototype.$axios = axios;

Vue.config.productionTip = false;

export const navigationDrawerBus = new Vue();

new Vue({
    router,
    vuetify,
    render: h => h(App)
}).$mount('#app');
