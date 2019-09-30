<template>
	<div class="members">
		<h1>회원 목록</h1>
		
		<v-flex xs12 sm6 md12 pt-6>
			<v-simple-table>
				<thead>
				<tr>
					<th class="text-left">회원 번호</th>
					<th class="text-left">이름</th>
					<th class="text-left">도시</th>
					<th class="text-left">주소</th>
					<th class="text-left">우편 번호</th>
				</tr>
				</thead>
				<tbody>
				<tr v-for="member in members" :key="member.name">
					<td>{{ member.id }}</td>
					<td>{{ member.name }}</td>
					<td>{{ member.city }}</td>
					<td>{{ member.street }}</td>
					<td>{{ member.zipCode }}</td>
				</tr>
				</tbody>
			</v-simple-table>
		</v-flex>
	</div>
</template>

<script>
    export default {
        name: "Members",
        data() {
            return {
                members: [
                    {
                        id: 1,
                        name: 'Luke',
                        city: '서울',
                        street: '송파',
                        zipCode: '05705'
                    },
                    {
                        id: 2,
                        name: 'wb',
                        city: '강릉',
                        street: '초당',
                        zipCode: '12345'
                    },
                ]
            }
        }
    }
</script>

<style scoped>

</style>