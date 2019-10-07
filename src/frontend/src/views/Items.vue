<template>
	<div class="items">
		<h1>상품 목록</h1>
		
		<v-flex xs12 sm6 md12 pt-6>
			<v-simple-table>
				<thead>
				<tr>
					<th class="text-left">상품 번호</th>
					<th class="text-left">상품명</th>
					<th class="text-left">가격</th>
					<th class="text-left">재고수량</th>
					<th class="text-left"></th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="item in items" :key="item.id">
					<td>{{ item.id }}</td>
					<td>{{ item.name }}</td>
					<td>{{ item.price }}</td>
					<td>{{ item.stockQuantity }}</td>
					<td>
						<v-btn @click="onEdit(item.id)" fab small icon color="light-green lighten-2">
							<v-icon>edit</v-icon>
						</v-btn>
					</td>
				</tr>
				</tbody>
			</v-simple-table>
		</v-flex>
		<Pagination :length="pageCount" @paginate="getPage"></Pagination>
	</div>
</template>

<script>
    import Pagination from "@/components/Pagination";

    const LIMIT = 3
    export default {
        name: "Items",
        components: {
            Pagination
        },
        data() {
            return {
                items: [],
                totalCount: 0
            }
        },
        methods: {
            async getPage(page = 1) {
                const offset = (parseInt(page) - 1) * LIMIT + 1
                const response = await this.$axios.get("/api/items", {
                    params: {offset: offset, limit: LIMIT}
                })
                this.items = response.data ? response.data : []
            },
            async getCount() {
                const response = await this.$axios.get("/api/items/count")
                this.totalCount = response.data
            },
            onEdit(id) {
                this.$router.push(`/items/${id}/edit`)
            }
        },
        computed: {
            pageCount() {
                let number = Math.ceil(this.totalCount / LIMIT);
                return number
            }
        },
        mounted() {
            this.getCount()
            this.getPage()
        }
    }
</script>

<style scoped>

</style>