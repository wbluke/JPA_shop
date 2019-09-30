<template>
	<div class="orders">
		<h1>주문 내역</h1>
		
		<v-flex xs12 sm6 md12 pt-6>
			<v-row>
				<v-col cols="12" sm="6" md="4">
					<v-text-field md4 label="검색" placeholder="회원 이름을 입력하세요" v-model="searchKeyword"
					              clearable></v-text-field>
				</v-col>
				<v-col cols="12" sm="4" md="3">
					<v-autocomplete v-model="status" label="주문 상태" :items="statusMenu"></v-autocomplete>
				</v-col>
				<v-flex my-2>
					<v-col cols="12" sm="4" md="3">
						<v-btn v-on:click="submit" fab dark small tile outlined color="light-green lighten-2"
						       class="mr-2">
							검색
						</v-btn>
					</v-col>
				</v-flex>
			</v-row>
			<v-simple-table>
				<thead>
				<tr>
					<th class="text-left">주문 번호</th>
					<th class="text-left">회원명</th>
					<th class="text-left">대표 상품</th>
					<th class="text-left">주문 가격</th>
					<th class="text-left">주문 수량</th>
					<th class="text-left">상태</th>
					<th class="text-left">일시</th>
					<th class="text-left" style="width: 10px;"></th>
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
					<td>{{ order.simpleOrderDate }}</td>
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
                searchKeyword: '',
                statusMenu: ['주문', '취소'],
                status: '',
                orders: [
                    {
                        id: 1,
                        memberName: 'Luke',
                        orderItem: '토비의 봄',
                        orderPrice: 50000,
                        count: 5,
                        status: 'ORDER',
                        simpleOrderDate: '2019-08-11'
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>