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
					<td>{{ member.zipcode }}</td>
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
                members: []
            }
        },
        created() {
            this.$axios
                .get('/api/members')
                .then(res => res.data)
                .then(data => {
                    this.members = data;
                })
        }
    }
</script>

<style scoped>

</style>