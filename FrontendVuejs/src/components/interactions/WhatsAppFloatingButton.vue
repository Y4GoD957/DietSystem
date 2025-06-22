<template>
  <div class="whatsapp-button" @click="redirectToWhatsapp" title="Fale conosco no WhatsApp">
    <img src="@/assets/imgs/whatsapp.png" alt="WhatsApp Logo" />
    <span v-if="showLabel" class="whatsapp-label">Fale conosco</span>
  </div>
</template>

<script>
export default {
  name: 'WhatsAppFloatingButton',
  props: {
    phone: {
      type: String,
      default: '5561981905006'
    },
    showLabel: {
      type: Boolean,
      default: false
    }
  },
  methods: {
    redirectToWhatsapp() {
      window.open(`https://wa.me/${this.phone}`, '_blank')
    }
  }
}
</script>

<style scoped>
.whatsapp-button {
  position: fixed;
  bottom: 20px;
  left: 20px;
  background-color: #25d366;
  border-radius: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.25);
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  z-index: 1000;
  padding: 12px;
  gap: 8px;
  transition: all 0.3s ease;
}

.whatsapp-button:hover {
  transform: translateY(-2px) scale(1.05);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.35);
}

.whatsapp-button img {
  width: 28px;
  height: 28px;
}

.whatsapp-label {
  font-size: 0.9rem;
  font-weight: 600;
  color: #fff;
  white-space: nowrap;
  animation: fadeIn 0.3s ease-in-out;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateX(-5px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

@media (max-width: 500px) {
  .whatsapp-label {
    display: none;
  }
}
</style>
