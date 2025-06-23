<template>
  <div v-if="showButton" class="scroll-to-top" @click="scrollToTop">
    <span>▲</span>
  </div>
</template>

<script>
export default {
  name: 'ScrollToTopButton',
  data() {
    return {
      showButton: false,
      scrollListener: null
    }
  },
  mounted() {
    // Adiciona o listener no scroll com otimização
    this.scrollListener = () => {
      window.requestAnimationFrame(this.handleScroll)
    }
    window.addEventListener('scroll', this.scrollListener, { passive: true })
  },
  beforeUnmount() {
    // Remove o listener no ciclo de vida correto
    window.removeEventListener('scroll', this.scrollListener)
  },
  methods: {
    handleScroll() {
      const scrollPosition = window.scrollY
      const totalScrollable = document.documentElement.scrollHeight - window.innerHeight
      this.showButton = scrollPosition > totalScrollable * 0.4
    },
    scrollToTop() {
      window.scrollTo({ top: 0, behavior: 'smooth' })
    }
  }
}
</script>

<style scoped>
.scroll-to-top {
  position: fixed;
  bottom: 20px;
  right: 20px;
  width: 50px;
  height: 50px;
  background-color: #000;
  color: #ffffff;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  box-shadow: 0 0 10px rgba(255, 255, 255, 0.2);
  z-index: 1000;
  transition: transform 0.3s;
}

.scroll-to-top:hover {
  transform: scale(1.1);
}
</style>
