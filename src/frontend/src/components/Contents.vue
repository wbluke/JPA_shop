<template>
	<v-card>
		<v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="100%">
			<v-container style="height: 700px;">
				
				<drawer></drawer>
			
			</v-container>
		</v-sheet>
	</v-card>
</template>

<script>
    import Drawer from './Drawer';

    export default {
        name: "MainContents",
        components: {
            Drawer
        }
    }
</script>
<style scoped>

</style>