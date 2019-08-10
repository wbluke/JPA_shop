<template>
	<v-navigation-drawer v-model="drawer" absolute bottom>
		<v-list nav dense>
			<v-list-item-group color="light-green">
				<v-list-item>
					<v-list-item-action>
						<v-icon>face</v-icon>
					</v-list-item-action>
					<v-list-item-title>회원 가입</v-list-item-title>
				</v-list-item>
				<v-list-item>
					<v-list-item-action>
						<v-icon>people</v-icon>
					</v-list-item-action>
					<v-list-item-title>회원 목록</v-list-item-title>
				</v-list-item>
				<v-divider inset></v-divider>
				<v-list-item>
					<v-list-item-action>
						<v-icon>playlist_add</v-icon>
					</v-list-item-action>
					<v-list-item-title>상품 등록</v-list-item-title>
				</v-list-item>
				<v-list-item>
					<v-list-item-action>
						<v-icon>list</v-icon>
					</v-list-item-action>
					<v-list-item-title>상품 목록</v-list-item-title>
				</v-list-item>
				<v-divider inset></v-divider>
				<v-list-item>
					<v-list-item-action>
						<v-icon>shopping_cart</v-icon>
					</v-list-item-action>
					<v-list-item-title>상품 주문</v-list-item-title>
				</v-list-item>
				<v-list-item>
					<v-list-item-action>
						<v-icon>receipt</v-icon>
					</v-list-item-action>
					<v-list-item-title>주문 내역</v-list-item-title>
				</v-list-item>
				<v-divider inset></v-divider>
			</v-list-item-group>
		</v-list>
	</v-navigation-drawer>
</template>

<script>
    import {navigationDrawerBus} from "../main";
    
    export default {
        name: "Drawer",
        data: () => ({
            drawer: false,
        }),

        created() {
            navigationDrawerBus.$on('toggleNavigationDrawer', (data) => {
                this.drawer = data;
            })
        }
    }
</script>

<style scoped>

</style>