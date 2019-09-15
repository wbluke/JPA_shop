<template>
	<div class="orders">
		<h1>주문 내역</h1>
		
		<v-flex xs12 sm6 md12 pt-6>
			<v-simple-table height="300">
				<thead>
				<tr>
					<th class="text-left">주문 번호</th>
					<th class="text-left">회원명</th>
					<th class="text-left">대표 상품</th>
					<th class="text-left">주문 가격</th>
					<th class="text-left">주문 수량</th>
					<th class="text-left">상태</th>
					<th class="text-left">일시</th>
					<th class="text-left"></th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="order in orders" :key="order.id">
					<td>{{ order.id }}</td>
					<td>{{ order.memberName }}</td>
					<td>{{ order.orderItem }}</td>
					<td>{{ order.orderPrice }}</td>
					<td>{{ order.count }}</td>
					<td>{{ order.status }}</td>
					<td>{{ order.orderDate }}</td>
					<td>
						<v-btn fab small icon color="red lighten-2">
							<v-icon>clear</v-icon>
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
        name: "Orders",
        data() {
            return {
                orders: [
                    {
                        id: 1,
                        memberName: 'Luke',
                        orderItem: '토비의 봄',
                        orderPrice: 50000,
                        count: 5,
                        status: 'ORDER',
                        orderDate: '2019-08-11'
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>