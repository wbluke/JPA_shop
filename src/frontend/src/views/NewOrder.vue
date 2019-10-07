<template>
	<div class="newOrder">
		<h1>상품 주문</h1>
		
		<v-flex xs12 sm6 md6 my-auto pt-6>
			<v-autocomplete label="주문 회원" :items="orderMemberNames" clearable></v-autocomplete>
			<v-autocomplete label="상품명" :items="itemNames" clearable></v-autocomplete>
			<v-text-field label="주문 수량" placeholder="주문 수량을 입력하세요" clearable></v-text-field>
			
			<v-layout justify-end>
				<v-btn fab dark small color="light-green lighten-2" class="mr-2">
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
                orderMember: '',
                item: '',
                count: '',
            }
        },
        methods: {
            submit: function () {

            },
            findMemberIdByName: function (name) {
                return this.members.find(member => member.name === name).id;
            },
            findItemIdByName: function (name) {
                return this.items.find(item => item.name === name).id;
            }
        },
        created() {

        }
    }
</script>

<style scoped>

</style>