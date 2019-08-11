<template>
	<nav>
		<v-card>
			<v-sheet id="scrolling-techniques-7" class="overflow-y-auto" max-height="100%">
				<v-container align-center justify-center fluid style="height: 700px;">
					
					<drawer></drawer>
					
					<v-layout align-center>
						<v-flex fill-height col-lg-6 col-md-8 mx-auto>
							<router-view></router-view>
						</v-flex>
					</v-layout>
				
				</v-container>
			</v-sheet>
		</v-card>
	
	</nav>
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