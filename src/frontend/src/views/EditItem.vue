<template>
	<div class="editItem">
		<h1>상품 수정</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-text-field v-model="form.name" label="상품명" placeholder="이름을 입력하세요" clearable></v-text-field>
			<v-text-field v-model="form.price" label="가격" placeholder="가격을 입력하세요" clearable></v-text-field>
			<v-text-field v-model="form.stockQuantity" label="수량" placeholder="수량을 입력하세요" clearable></v-text-field>
			
			<v-layout justify-end>
				<v-btn @click="onUpdate" fab dark small color="light-green lighten-2" class="mr-2">
					<v-icon>done</v-icon>
				</v-btn>
				<v-btn @click="clearForm()" fab dark small color="red lighten-2">
					<v-icon>clear</v-icon>
				</v-btn>
			</v-layout>
		</v-flex>
	</div>
</template>

<script>
    const initialData = () => ({
        name: '',
        price: '',
        stockQuantity: '',
    })

    export default {
        name: "NewItem",
        data: () => ({
            form: initialData()
        }),
        methods: {
            async getItem() {
                const response = await this.$axios.get(`/api/items/${this.$route.params.item_id}`)
                this.form = response.data
            },
            async onUpdate() {
                await this.$axios.put(`/api/items/${this.$route.params.item_id}`, this.form)
                await this.$router.push({name: 'items'})
            },
            clearForm() {
                this.form = initialData()
            }
        },
        mounted() {
            this.getItem()
        }
    }
</script>

<style scoped>

</style>