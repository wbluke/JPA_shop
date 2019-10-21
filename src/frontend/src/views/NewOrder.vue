<template>
	<div class="newOrder">
		<h1>상품 주문</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-autocomplete label="주문 회원" :items="orderMemberNames" v-model="form.memberName"
			                clearable></v-autocomplete>
			<v-autocomplete label="상품명" :items="itemNames" v-model="form.itemName" clearable></v-autocomplete>
			<v-text-field label="주문 수량" placeholder="주문 수량을 입력하세요" v-model="form.count" clearable></v-text-field>
			
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
        name: "NewOrder",
        data() {
            return {
                members: [],
                items: [],
                orderMemberNames: [],
                itemNames: [],
                form: {
                    memberName: '',
                    itemName: '',
                    count: '',
                }
            }
        },
        methods: {
            submit: function () {
                this.$axios.post('/api/orders', {
                    memberId: this.findMemberIdByName(this.form.memberName),
                    itemId: this.findItemIdByName(this.form.itemName),
                    count: this.form.count
                }).then(res => {
                    console.log(res);
                    if (res.status === 200) {
                        this.$router.push({name: 'orders'})
                    }
                })
            },
            findMemberIdByName: function (name) {
                return this.members.find(member => member.name === name).id;
            },
            findItemIdByName: function (name) {
                return this.items.find(item => item.name === name).id;
            }
        },
        created() {
            this.$axios.get('/api/members')
                .then(res => res.data)
                .then(data => {
                    console.log(data);
                    this.members = data;
                    data.forEach(member => {
                        this.orderMemberNames.push(member.name);
                    })
                })

            this.$axios.get('/api/items/all')
                .then(res => res.data)
                .then(data => {
                    console.log(data);
                    this.items = data;
                    data.forEach(item => {
                        this.itemNames.push(item.name);
                    })
                })
        }
    }
</script>

<style scoped>

</style>