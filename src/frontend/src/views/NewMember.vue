<template>
	<div class="newMember">
		<h1>회원 가입</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-text-field v-model="name" label="이름" placeholder="이름을 입력하세요" clearable></v-text-field>
			<v-text-field v-model="city" label="도시" placeholder="도시를 입력하세요" clearable></v-text-field>
			<v-text-field v-model="street" label="거리" placeholder="거리를 입력하세요" clearable></v-text-field>
			<v-text-field v-model="zipcode" label="우편 번호" placeholder="우편 번호를 입력하세요" clearable></v-text-field>
			
			<v-layout justify-end>
				<v-btn v-on:click="submit" fab dark small color="light-green lighten-2" class="mr-2">
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
        name: "NewMember",
        data() {
            return {
                name: '',
                city: '',
                street: '',
                zipcode: '',
            }
        },
        methods: {
            submit: function () {
                this.$axios
                    .post('/api/members', {
                        name: this.name,
                        city: this.city,
                        street: this.street,
                        zipcode: this.zipcode,
                    })
                    .then(res => res.data)
                    .then(data => {
                        window.console.log(data);
                        window.location = '/#/members';
                    })
            }
        }
    }
</script>

<style scoped>

</style>