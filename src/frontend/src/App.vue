<template>
	<v-app>
		<header-bar></header-bar>
		<contents></contents>
	</v-app>
</template>

<script>
    import HeaderBar from './components/HeaderBar';
    import Contents from './components/Contents';

    export default {
        name: 'App',
        components: {
            HeaderBar,
            Contents
        },
        data: () => ({
            //
        }),
    };
</script>
