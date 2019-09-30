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
						<v-btn fab small icon color="light-green lighten-2">
							<v-icon>edit</v-icon>
						</v-btn>
					</td>
				</tr>
				</tbody>
			</v-simple-table>
		</v-flex>
	</div>
</template>

<script>
    export default {
        name: "Items",
        data() {
            return {
                items: [
                    {
                        id: 1,
                        name: '토비의 봄',
                        price: 50000,
                        stockQuantity: 5
                    },
                    {
                        id: 2,
                        name: '자바 ORM 표준 JPA 프로그래밍',
                        price: 43000,
                        stockQuantity: 20
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>