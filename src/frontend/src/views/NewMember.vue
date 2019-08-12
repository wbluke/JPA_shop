<template>
	<div class="newMember">
		<h1>회원 가입</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-text-field label="이름" placeholder="이름을 입력하세요" clearable></v-text-field>
			<v-text-field label="도시" placeholder="도시를 입력하세요" clearable></v-text-field>
			<v-text-field label="거리" placeholder="거리를 입력하세요" clearable></v-text-field>
			<v-text-field label="우편 번호" placeholder="우편 번호를 입력하세요" clearable></v-text-field>
			
			<v-layout justify-end>
				<v-btn fab dark small color="light-green lighten-2" class="mr-2">
					<v-icon>done</v-icon>
				</v-btn>
				<v-btn fab dark small color="red lighten-2">
					<v-icon>clear</v-icon>
				</v-btn>
			</v-layout>
		</v-flex>
	
	
	</div>
</template>

<script>
    export default {
        name: "NewMember"
    }
</script>

<style scoped>

</style>