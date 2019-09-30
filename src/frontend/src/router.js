import Vue from 'vue';
import Router from 'vue-router';

import Home from './views/Home';
import NewMember from './views/NewMember';
import Members from './views/Members';
import NewItem from './views/NewItem';
import EditItem from './views/EditItem';
import Items from './views/Items';
import NewOrder from './views/NewOrder';
import Orders from './views/Orders';

Vue.use(Router);

export default new Router({
    // mode: history,
    base: process.env.BASE_URL,
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/members/new',
            name: 'newMember',
            component: NewMember
        },
        {
            path: '/members',
            name: 'members',
            component: Members
        },
        {
            path: '/items/new',
            name: 'newItem',
            component: NewItem
        },
        {
            path: '/items/:item_id/edit',
            name: 'editItem',
            component: EditItem
        },
        {
            path: '/items',
            name: 'items',
            component: Items
        },
        {
            path: '/order',
            name: 'newOrder',
            component: NewOrder
        },
        {
            path: '/orders',
            name: 'orders',
            component: Orders
        },
    ]
})