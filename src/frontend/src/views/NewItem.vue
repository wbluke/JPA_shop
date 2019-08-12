<template>
	<div class="newItem">
		<h1>상품 등록</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-text-field label="상품명" placeholder="이름을 입력하세요" clearable></v-text-field>
			<v-text-field label="가격" placeholder="가격을 입력하세요" clearable></v-text-field>
			<v-text-field label="수량" placeholder="수량을 입력하세요" clearable></v-text-field>
			<v-text-field label="저자" placeholder="저자를 입력하세요" clearable></v-text-field>
			<v-text-field label="ISBN" placeholder="ISBN을 입력하세요" clearable></v-text-field>
			
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
        name: "NewItem"
    }
</script>

<style scoped>

</style>