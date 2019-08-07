<template>
	<v-card class="overflow-hidden">
		<v-app-bar
						absolute
						color="white"
						elevate-on-scroll
						scroll-target="#scrolling-techniques-7"
		>
			<v-app-bar-nav-icon></v-app-bar-nav-icon>
			
			<v-toolbar-title>JPA Shop</v-toolbar-title>
			
			<v-spacer></v-spacer>
		
		</v-app-bar>
		<v-sheet
						id="scrolling-techniques-7"
						class="overflow-y-auto"
						max-height="800"
		>
			<v-container style="height: 1500px;">
			
			</v-container>
		</v-sheet>
	</v-card>
</template>

<script>
    export default {
        name: "HeaderBar"
    }
</script>

<style scoped>

</style>