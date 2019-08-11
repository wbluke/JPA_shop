<template>
	<v-navigation-drawer v-model="drawer" absolute>
		<v-list nav dense>
			<v-list-item-group color="light-green">
				<div v-for="(link, index) in links" :key="link.text">
					<v-list-item link router :to="link.route" exact>
						
						<v-list-item-action>
							<v-icon>{{ link.icon }}</v-icon>
						</v-list-item-action>
						<v-list-item-title>{{ link.text }}</v-list-item-title>
					
					</v-list-item>
					<v-divider inset v-if="index % 2 === 1"></v-divider>
				</div>
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
            links: [
                {text: '회원 가입', icon: 'face', route: '/members/new'},
                {text: '회원 목록', icon: 'people', route: '/members'},
                {text: '상품 등록', icon: 'playlist_add', route: '/items/new'},
                {text: '상품 목록', icon: 'list', route: '/items'},
                {text: '상품 주문', icon: 'shopping_cart', route: '/order'},
                {text: '주문 내역', icon: 'receipt', route: '/orders'},
            ]
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