<template>
	<v-card color="grey lighten-4" flat tile>
		<v-toolbar>
			<v-app-bar-nav-icon @click.stop="toggleNavigationDrawer"></v-app-bar-nav-icon>
			
			<v-toolbar-title>JPA Shop</v-toolbar-title>
			
			<v-spacer></v-spacer>
			
			<v-btn text icon color="light-green" router to="/" exact>
				<v-icon>home</v-icon>
			</v-btn>
		
		</v-toolbar>
	</v-card>
</template>

<script>
    import {navigationDrawerBus} from "../main";

    export default {
        name: "HeaderBar",
        data: () => ({
            drawer: false
        }),

        methods: {
            toggleNavigationDrawer: function () {
                this.drawer = !this.drawer;
                navigationDrawerBus.$emit('toggleNavigationDrawer', this.drawer);
            }
        },
    }
</script>
<style scoped>

</style>