<template>
	<div class="text-center my-3">
		<v-pagination @input="paginate" v-model="page" :length="length" :total-visible="totalVisible"></v-pagination>
	</div>
</template>

<script>
    export default {
        name: "Pagination",
        data() {
            return {
                page: 1,
            }
        },
        props: {
            length: {
                type: Number,
                default: 10
            },
            totalVisible: {
                type: Number,
                default: 11
            }
        },
        methods: {
            paginate: function () {
                this.$emit('paginate', this.page);
            }
        }
    }
</script>

<style scoped>

</style>