<template>
	<div class="home">
		<h1>Home</h1>
		
		<v-card class="my-6">
			<v-container>
				<v-layout>
					<v-flex md4>
						<v-img class="white--text" height="350px"
						       src="http://bimage.interpark.com/goods_image/5/9/5/3/240925953g.jpg" contain>
						</v-img>
					</v-flex>
					<v-flex md8>
						<v-card-text>
							<span>In KI-Workshop</span><br>
							<span class="text--primary font-weight-black title">
                                <span>JPA Shop admin page for studying JPA</span>
                            </span>
						</v-card-text>
						
						<v-card-actions>
							<v-btn href="https://github.com/wbluke/JPA_shop" text color="green">
								Go to github
							</v-btn>
						</v-card-actions>
					</v-flex>
				</v-layout>
			</v-container>
		
		</v-card>
	
	</div>
</template>

<script>
    export default {
        name: "Home"
    }
</script>

<style scoped>

</style>