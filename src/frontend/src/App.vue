<template>
	<v-app>
		<header-bar></header-bar>
		<main-contents></main-contents>
	</v-app>
</template>

<script>
    import HeaderBar from './components/HeaderBar';
    import MainContents from './components/MainContents';

    export default {
        name: 'App',
        components: {
            HeaderBar,
            MainContents
        },
        data: () => ({
            //
        }),
    };
</script>
