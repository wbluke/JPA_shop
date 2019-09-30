<template>
	<div class="editItem">
		<h1>상품 수정</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-text-field v-model="name" label="상품명" placeholder="이름을 입력하세요" clearable></v-text-field>
			<v-text-field v-model="price" label="가격" placeholder="가격을 입력하세요" clearable></v-text-field>
			<v-text-field v-model="stockQuantity" label="수량" placeholder="수량을 입력하세요" clearable></v-text-field>
			
			<v-layout justify-end>
				<v-btn v-on:click="submit" fab dark small color="light-green lighten-2" class="mr-2">
					<v-icon>done</v-icon>
				</v-btn>
				<v-btn v-on:click="clear" fab dark small color="red lighten-2">
					<v-icon>clear</v-icon>
				</v-btn>
			</v-layout>
		</v-flex>
	</div>
</template>

<script>
    function initialize() {
        return {
            name: '',
            price: '',
            stockQuantity: '',
        }
    }

    export default {
        name: "NewItem",
        data() {
            return {
                name: '',
                price: '',
                stockQuantity: '',
            }
        },
        methods: {
            submit: function () {
                const itemId = this.$route.params.item_id;
                window.console.log(itemId);

                this.$axios
                    .post('/api/items/' + itemId + '/edit', {
                        name: this.name,
                        price: this.price,
                        stockQuantity: this.stockQuantity,
                    })
                    .then(res => {
                        window.console.log(res.data);
                        window.location = '/#/items';
                    })
            },
            clear: function () {
                Object.assign(this.$data, initialize());
            }
        },
        created() {
            this.$axios
                .get('/api/items/' + this.$route.params.item_id)
                .then(res => {
                    return res.data;
                })
                .then(data => {
                    window.console.log(data);
                    this.name = data.name;
                    this.price = data.price;
                    this.stockQuantity = data.stockQuantity;
                })
        }
    }
</script>

<style scoped>

</style>