<template>
<TheHeader />

<softScroll />
</template>

<script>
import TheHeader from '@/components/TheHeader.vue';
import softScroll from '@/components/softScroll.js';

export default {
    name: 'DietCalculator',
    components: {
        TheHeader,
        softScroll
    }
}
</script>

<style>

</style>